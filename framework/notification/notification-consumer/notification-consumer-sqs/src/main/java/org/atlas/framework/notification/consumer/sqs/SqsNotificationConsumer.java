package org.atlas.framework.notification.consumer.sqs;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.commons.util.json.JsonUtil;
import org.atlas.framework.notification.core.Notification;
import org.atlas.framework.notification.core.NotificationDispatcher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SqsNotificationConsumer {

    @Value("${app.notification.queue.sqs.notification-queue-url}")
    private String requestQueueUrl;

    private final SqsClient sqsClient;
    private final NotificationDispatcher notificationDispatcher;

    @PostConstruct
    public void init() {
        // Separate new thread to avoid the startup process if occur exception
        new Thread(this::consumeMessages).start();
    }

    public void consumeMessages() {
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(requestQueueUrl)
                // Receive up to 10 messages at a time
                .maxNumberOfMessages(10)
                // Enable long polling:
                // wait until a message is available before sending a response,
                // rather than returning immediately if no messages are available.
                .waitTimeSeconds(20)
                .build();

        while (true) {
            List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).messages();
            if (!CollectionUtils.isEmpty(messages)) {
                messages.forEach(message -> {
                    log.info("Received message: {}", message);
                    try {
                        processMessage(message);

                        // SQS Standard queue supports at-least-once delivery.
                        // After a message is polled by a consumer, it becomes invisible to other consumers in 30 seconds (default).
                        // That means the message has 30 seconds to be processed.
                        // After the message visibility timeout is over, the message is "visible" in SQS.
                        // So in some scenarios, we need to delete messages from queue after processed them.
                        deleteMessage(message);
                    } catch (Exception e) {
                        log.error("Failed to process message: messageId={}", message.messageId(), e);
                    }
                });
            }
        }
    }

    private void processMessage(Message message) {
        Notification notification = JsonUtil.toObject(message.body(), Notification.class);
        notificationDispatcher.dispatch(notification);
    }

    private void deleteMessage(Message message) {
        DeleteMessageRequest deleteMessageRequest = DeleteMessageRequest.builder()
                .queueUrl(requestQueueUrl)
                .receiptHandle(message.receiptHandle())
                .build();
        sqsClient.deleteMessage(deleteMessageRequest);
        log.info("Deleted message: messageId={}", message.messageId());
    }
}
