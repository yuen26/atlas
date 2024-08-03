package org.atlas.framework.notification.consumer.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.framework.notification.core.Notification;
import org.atlas.framework.notification.core.NotificationDispatcher;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitNotificationConsumer {

    private static final int MAX_RETRIES_COUNT = 3;

    private final RabbitTemplate rabbitTemplate;
    private final NotificationDispatcher notificationDispatcher;

    @RabbitListener(queues = RabbitConsumerConfig.NOTIFICATION_QUEUE)
    public void consumeMessage(Message<Notification> message) throws Exception {
        Notification notification = message.getPayload();
        MessageHeaders headers = message.getHeaders();
        log.info("Received message: payload={}, headers={}", notification, headers);
        processMessage(notification, headers);
    }

    private void processMessage(Notification notification, MessageHeaders headers) {
        try {
            notificationDispatcher.dispatch(notification);
        } catch (Exception e) {
            // Check whether reached the max retries
            if (reachedMaxRetriesCount(headers)) {
                sendNotificationToDlq(notification);
                return;
            }
            throw e;
        }
    }

    private boolean reachedMaxRetriesCount(MessageHeaders headers) {
        Long deliveryCount = (Long) headers.get("x-delivery-count");
        return deliveryCount != null && deliveryCount >= MAX_RETRIES_COUNT;
    }

    private void sendNotificationToDlq(Object request) {
        try {
            rabbitTemplate.convertAndSend(RabbitConsumerConfig.NOTIFICATION_DLQ_ROUTING_KEY, request);
            log.info("Published request to DLQ: {}", request);
        } catch (Exception e) {
            log.error("Failed to publish request to DLQ: {}", request, e);
        }
    }
}
