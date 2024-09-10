package org.atlas.business.order.application.event.local;

import lombok.RequiredArgsConstructor;
import org.atlas.commons.utils.json.JsonUtil;
import org.atlas.framework.event.contract.order.OrderConfirmedEvent;
import org.atlas.framework.notification.core.Notification;
import org.atlas.framework.notification.core.NotificationId;
import org.atlas.framework.notification.core.NotificationPublisher;
import org.atlas.framework.notification.core.model.Order001Data;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderConfirmedEventHandler {

    private final NotificationPublisher notificationPublisher;

    @EventListener
    public void publishNotification(OrderConfirmedEvent event) throws Exception {
        Order001Data order001Data = new Order001Data(event.getOrder().getId());
        String notificationData = JsonUtil.toJson(order001Data);
        Notification notification = new Notification(NotificationId.ORDER_001, notificationData);
        notificationPublisher.publish(notification);
    }
}
