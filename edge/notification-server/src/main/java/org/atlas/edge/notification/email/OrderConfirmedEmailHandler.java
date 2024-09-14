package org.atlas.edge.notification.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.framework.event.contract.EventType;
import org.atlas.framework.event.contract.order.OrderConfirmedEvent;
import org.atlas.framework.event.core.consumer.handler.EventHandler;
import org.atlas.framework.notification.email.core.model.Attachment;
import org.atlas.framework.notification.email.core.model.SendEmailRequest;
import org.atlas.framework.notification.email.core.service.EmailService;
import org.atlas.framework.template.contract.TemplateResolver;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderConfirmedEmailHandler implements EventHandler<OrderConfirmedEvent> {

    private static final String SUBJECT_BASE_DIR = "email/subject";
    private static final String BODY_BASE_DIR = "email/body";
    private static final String ATTACHMENT_DIR = "templates/email/attachment";

    private final TemplateResolver templateResolver;
    private final EmailService emailService;

    @Override
    public EventType supports() {
        return EventType.ORDER_CONFIRMED;
    }

    @Async
    @Override
    public void handle(OrderConfirmedEvent event) {
        OrderDto order = event.getOrder();

        // Subject
        String subject;
        try {
            subject = templateResolver.resolve(SUBJECT_BASE_DIR + "/order_confirmed.ftl");
        } catch (Exception e) {
            throw new RuntimeException("Could not resolve subject template", e);
        }

        // Body
        Map<String, Object> model = new HashMap<>();
        model.put("order", order);
        String body;
        try {
            body = templateResolver.resolve(BODY_BASE_DIR + "/order_confirmed.ftl", model);
        } catch (Exception e) {
            throw new RuntimeException("Could not resolve body template", e);
        }

        // Attachments (demo)
        Attachment attachment;
        ClassPathResource attachmentResource = new ClassPathResource(ATTACHMENT_DIR + "/coffee.jpg");
        File attachmentFile;
        try {
            attachmentFile = attachmentResource.getFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        attachment = new Attachment("coffee.jpg", attachmentFile);

        SendEmailRequest request = new SendEmailRequest.Builder()
            .addRecipient(order.getCustomer().getEmail())
            .setSubject(subject)
            .setBody(body)
            .addAttachment(attachment)
            .setHtml(true)
            .build();
        emailService.send(request);
        log.info("Sent email: event={}", event);
    }
}
