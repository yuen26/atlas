package org.atlas.framework.email.core.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.business.user.application.contract.model.UserDto;
import org.atlas.framework.api.client.contract.IUserServiceClient;
import org.atlas.framework.email.core.commons.Constant;
import org.atlas.framework.email.core.model.Attachment;
import org.atlas.framework.email.core.model.SendEmailRequest;
import org.atlas.framework.email.core.service.EmailService;
import org.atlas.framework.notification.core.NotificationId;
import org.atlas.framework.notification.core.model.User001Data;
import org.atlas.framework.template.contract.TemplateResolver;
import org.atlas.shared.exception.AppError;
import org.atlas.shared.exception.BusinessException;
import org.atlas.shared.util.json.JsonUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class User001EmailHandler extends EmailHandler {

    private final IUserServiceClient userServiceClient;
    private final TemplateResolver templateResolver;
    private final EmailService sendEmailService;

    @Override
    public NotificationId notificationId() {
        return NotificationId.USER_001;
    }

    @Override
    public void handle(String data) {
        // Prepare data
        User001Data user001Data = JsonUtil.toObject(data, User001Data.class);
        UserDto userDto = userServiceClient.getUser(user001Data.getUserId())
            .orElseThrow(() -> new BusinessException(AppError.USER_NOT_FOUND));

        // Subject
        String subject;
        try {
            subject = templateResolver.resolve(Constant.SUBJECT_TEMPLATE_DIR + "/user001.ftl");
        } catch (Exception e) {
            throw new RuntimeException("Could not resolve subject template", e);
        }

        // Body
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("customer", userDto);
        String body;
        try {
            body = templateResolver.resolve(Constant.BODY_TEMPLATE_DIR + "/user001.ftl", templateData);
        } catch (Exception e) {
            throw new RuntimeException("Could not resolve body template", e);
        }

        // Attachments (demo)
        Attachment attachment;
        ClassPathResource attachmentResource = new ClassPathResource(Constant.ATTACHMENT_DIR + "/coffee.jpg");
        File attachmentFile;
        try {
            attachmentFile = attachmentResource.getFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        attachment = new Attachment("coffee.jpg", attachmentFile);

        SendEmailRequest request = new SendEmailRequest.Builder()
            .addRecipient(userDto.getEmail())
            .setSubject(subject)
            .setBody(body)
            .addAttachment(attachment)
            .setHtml(true)
            .build();
        sendEmailService.send(request);
        log.info("Sent email: notificationId={}, data={}", notificationId(), data);
    }
}
