package org.atlas.framework.notification.publisher.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.framework.notification.core.Notification;
import org.atlas.framework.notification.core.NotificationPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitNotificationPublisher implements NotificationPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publish(Notification notification) {
        rabbitTemplate.convertAndSend(RabbitPublisherConfig.NOTIFICATION_ROUTING_KEY, notification);
        log.info("Enqueued notification: {}", notification);
    }
}
