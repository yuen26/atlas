package org.atlas.framework.notification.consumer.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitNotificationDlqConsumer {

    @RabbitListener(queues = RabbitConsumerConfig.NOTIFICATION_DLQ_QUEUE)
    public void consumeMessage(Message<Object> message) {
        Object payload = message.getPayload();
        MessageHeaders headers = message.getHeaders();
        log.info("<DLQ> Received message: payload={}, headers={}", payload, headers);
    }
}
