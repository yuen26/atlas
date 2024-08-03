package org.atlas.framework.notification.core;

public interface NotificationPublisher {

    void publish(Notification notification) throws Exception;
}
