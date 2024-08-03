package org.atlas.framework.event.snsfanout.consumer;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.atlas.framework.event.contract.DomainEvent;
import org.atlas.framework.event.core.handler.EventDispatcher;
import org.atlas.shared.util.json.JsonUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SnsFanoutEventConsumer {

    @Value("${app.event.sns-fanout.consumer.queues}")
    private List<String> queues;

    private final SqsClient sqsClient;
    private final EventDispatcher eventDispatcher;

    @PostConstruct
    public void init() {
        if (CollectionUtils.isNotEmpty(queues)) {
            queues.forEach(queue -> {
                // Separate new thread to avoid the startup process if occur exception
                new Thread(() -> consumeMessages(queue)).start();
            });
        } else {
            log.warn("No subscribed topics");
        }
    }

    private void consumeMessages(String queueUrl) {
        ReceiveMessageRequest receiveRequest = ReceiveMessageRequest.builder()
            .queueUrl(queueUrl)
            // Receive up to 10 messages at a time
            .maxNumberOfMessages(10)
            // Enable long polling:
            // wait until a message is available before sending a response,
            // rather than returning immediately if no messages are available.
            .waitTimeSeconds(20)
            .build();

        while (true) {
            List<Message> messages = sqsClient.receiveMessage(receiveRequest).messages();
            if (CollectionUtils.isNotEmpty(messages)) {
                messages.forEach(message -> {
                    log.info("Received message: {}", message);
                    try {
                        handleMessage(message);

                        // SQS Standard queue supports at-least-once delivery.
                        // After a message is polled by a consumer, it becomes invisible to other consumers in 30 seconds (default).
                        // That means the message has 30 seconds to be processed.
                        // After the message visibility timeout is over, the message is "visible" in SQS.
                        // So in some scenarios, we need to delete messages from queue after processed them.
                        deleteMessage(message, queueUrl, log);
                    } catch (Exception e) {
                        log.error("Failed to process message: messageId={}", message.messageId(), e);
                    }
                });
            }
        }
    }

    private void handleMessage(Message message) {
        DomainEvent event = JsonUtil.toObject(message.body(), DomainEvent.class);
        eventDispatcher.dispatch(event);
    }

    private void deleteMessage(Message message, String queueUrl, Logger log) {
        DeleteMessageRequest deleteMessageRequest = DeleteMessageRequest.builder()
            .queueUrl(queueUrl)
            .receiptHandle(message.receiptHandle())
            .build();
        sqsClient.deleteMessage(deleteMessageRequest);
        log.info("Deleted message: messageId={}", message.messageId());
    }
}