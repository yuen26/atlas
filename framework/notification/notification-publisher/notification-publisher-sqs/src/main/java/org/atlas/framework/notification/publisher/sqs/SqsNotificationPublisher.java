package org.atlas.framework.notification.publisher.sqs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.commons.util.json.JsonUtil;
import org.atlas.framework.notification.core.Notification;
import org.atlas.framework.notification.core.NotificationPublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Component
@RequiredArgsConstructor
@Slf4j
public class SqsNotificationPublisher implements NotificationPublisher {

    @Value("${app.notification.queue.sqs.notification-queue-url}")
    private String notificationQueueUrl;

    private final SqsClient sqsClient;

    @Override
    public void publish(Notification notification) {
        String messageBody = JsonUtil.toJson(notification);
        SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
            .queueUrl(notificationQueueUrl)
            .messageBody(messageBody)
            .build();
        sqsClient.sendMessage(sendMessageRequest);
        log.info("Enqueued notification: {}", notification);
    }
}
