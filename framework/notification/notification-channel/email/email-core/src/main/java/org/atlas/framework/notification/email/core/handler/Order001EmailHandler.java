package org.atlas.framework.notification.email.core.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.commons.exception.AppError;
import org.atlas.commons.exception.BusinessException;
import org.atlas.commons.utils.json.JsonUtil;
import org.atlas.framework.api.client.contract.order.IOrderServiceClient;
import org.atlas.framework.notification.core.NotificationId;
import org.atlas.framework.notification.core.model.Order001Data;
import org.atlas.framework.notification.email.core.model.SendEmailRequest;
import org.atlas.framework.notification.email.core.service.EmailService;
import org.atlas.framework.template.contract.TemplateResolver;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class Order001EmailHandler extends EmailHandler {

    private static final String SUBJECT_BASE_DIR = "email/subject";
    private static final String BODY_BASE_DIR = "email/body";

    private final IOrderServiceClient orderServiceClient;
    private final TemplateResolver templateResolver;
    private final EmailService emailService;

    @Override
    public NotificationId notificationId() {
        return NotificationId.ORDER_001;
    }

    @Override
    public void handle(String data) {
        // Prepare data
        Order001Data order001Data = JsonUtil.toObject(data, Order001Data.class);
        OrderDto orderDto = orderServiceClient.getOrder(order001Data.getId())
            .orElseThrow(() -> new BusinessException(AppError.ORDER_NOT_FOUND));

        // Subject
        String subject;
        try {
            subject = templateResolver.resolve(SUBJECT_BASE_DIR + "/order001.ftl");
        } catch (Exception e) {
            throw new RuntimeException("Could not resolve subject template", e);
        }

        // Body
        Map<String, Object> model = new HashMap<>();
        model.put("order", orderDto);
        String body;
        try {
            body = templateResolver.resolve(BODY_BASE_DIR + "/order001.ftl", model);
        } catch (Exception e) {
            throw new RuntimeException("Could not resolve body template", e);
        }

        SendEmailRequest request = new SendEmailRequest.Builder()
            .addRecipient(orderDto.getCustomer().getEmail())
            .setSubject(subject)
            .setBody(body)
            .setHtml(true)
            .build();
        emailService.send(request);
        log.info("Sent email: notificationId={}, data={}", notificationId(), data);
    }
}
