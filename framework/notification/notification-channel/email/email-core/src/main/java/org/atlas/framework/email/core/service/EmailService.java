package org.atlas.framework.email.core.service;

import org.atlas.framework.email.core.model.SendEmailRequest;

public interface EmailService {

    void send(SendEmailRequest request);
}
