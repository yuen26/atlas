package org.atlas.framework.notification.publisher.activemq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.framework.notification.core.Notification;
import org.atlas.framework.notification.core.NotificationPublisher;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ActivemqNotificationPublisher implements NotificationPublisher {

    private final JmsTemplate jmsTemplate;

    @Override
    public void publish(Notification notification) {
        jmsTemplate.convertAndSend(ActivemqPublisherConfig.NOTIFICATION_QUEUE, notification);
        log.info("Enqueued notification: {}", notification);
    }
}
