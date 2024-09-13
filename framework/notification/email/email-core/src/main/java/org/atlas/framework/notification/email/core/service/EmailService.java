package org.atlas.framework.notification.email.core.service;

import org.atlas.framework.notification.email.core.model.SendEmailRequest;

public interface EmailService {

    void send(SendEmailRequest request);
}
