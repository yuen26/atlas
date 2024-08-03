package org.atlas.framework.notification.consumer.sqs;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SqsNotificationDlqConsumer {

    @Value("${app.notification.queue.sqs.notification-dlq-url}")
    private String notificationDlqUrl;

    private final SqsClient sqsClient;

    @PostConstruct
    public void init() {
        // Separate new thread to avoid the startup process if occur exception
        new Thread(this::consumeMessages).start();
    }

    public void consumeMessages() {
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(notificationDlqUrl)
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
                messages.forEach(message -> log.info("<DLQ> Received message: {}", message));
            }
        }
    }
}
