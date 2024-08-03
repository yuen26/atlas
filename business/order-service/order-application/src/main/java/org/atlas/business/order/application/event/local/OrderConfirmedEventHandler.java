package org.atlas.business.order.application.event.local;

import lombok.RequiredArgsConstructor;
import org.atlas.framework.event.contract.order.OrderConfirmedEvent;
import org.atlas.framework.notification.core.Notification;
import org.atlas.framework.notification.core.NotificationId;
import org.atlas.framework.notification.core.NotificationPublisher;
import org.atlas.framework.notification.core.model.Order001Data;
import org.atlas.framework.realtime.contract.RealtimeService;
import org.atlas.shared.util.json.JsonUtil;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderConfirmedEventHandler {

    private final List<RealtimeService> realtimeServices;
    private final NotificationPublisher notificationPublisher;

    @EventListener
    public void publishRealtimeUpdate(OrderConfirmedEvent event) {
        realtimeServices.forEach(realtimeService -> realtimeService.publish(event));
    }

    @EventListener
    public void publishNotification(OrderConfirmedEvent event) throws Exception {
        Order001Data order001Data = new Order001Data(event.getOrder().getOrderId());
        String notificationData = JsonUtil.toJson(order001Data);
        Notification notification = new Notification(NotificationId.ORDER_001, notificationData);
        notificationPublisher.publish(notification);
    }
}
