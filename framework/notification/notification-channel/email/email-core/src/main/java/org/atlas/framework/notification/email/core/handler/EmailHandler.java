package org.atlas.framework.notification.email.core.handler;

import org.atlas.framework.notification.core.NotificationChannel;
import org.atlas.framework.notification.core.NotificationHandler;

public abstract class EmailHandler implements NotificationHandler {

    @Override
    public NotificationChannel channel() {
        return NotificationChannel.EMAIL;
    }
}
