package org.atlas.framework.notification.consumer.activemq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.framework.notification.core.Notification;
import org.atlas.framework.notification.core.NotificationDispatcher;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ActivemqNotificationConsumer {

    private final NotificationDispatcher notificationDispatcher;

    @JmsListener(
        destination = ActivemqConsumerConfig.NOTIFICATION_QUEUE,
        containerFactory = "jmsListenerContainerFactory"
    )
    public void consumeMessage(Notification notification) {
        log.info("Received notification {}", notification);
        processMessage(notification);
    }

    private void processMessage(Notification notification) {
        notificationDispatcher.dispatch(notification);
    }
}
