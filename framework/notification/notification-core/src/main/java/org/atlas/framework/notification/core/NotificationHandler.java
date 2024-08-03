package org.atlas.framework.notification.core;

public interface NotificationHandler {

    NotificationId notificationId();

    NotificationChannel channel();

    void handle(String data);
}
