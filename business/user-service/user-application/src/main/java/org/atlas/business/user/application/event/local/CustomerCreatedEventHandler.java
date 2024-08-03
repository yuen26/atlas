package org.atlas.business.user.application.event.local;

import lombok.RequiredArgsConstructor;
import org.atlas.framework.event.contract.user.CustomerCreatedEvent;
import org.atlas.framework.notification.core.Notification;
import org.atlas.framework.notification.core.NotificationId;
import org.atlas.framework.notification.core.NotificationPublisher;
import org.atlas.framework.notification.core.model.User001Data;
import org.atlas.shared.util.json.JsonUtil;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerCreatedEventHandler {

    private final NotificationPublisher notificationPublisher;

    @EventListener
    public void handle(CustomerCreatedEvent event) throws Exception {
        User001Data user001Data = new User001Data(event.getCustomer().getId());
        String notificationData = JsonUtil.toJson(user001Data);
        Notification notification = new Notification(NotificationId.USER_001, notificationData);
        notificationPublisher.publish(notification);
    }
}
