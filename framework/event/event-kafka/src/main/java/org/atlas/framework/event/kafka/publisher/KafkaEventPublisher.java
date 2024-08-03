package org.atlas.framework.event.kafka.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.framework.event.contract.DomainEvent;
import org.atlas.framework.event.core.publisher.EventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaEventPublisher implements EventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void publish(DomainEvent event) {
        String topic = event.type().name().toLowerCase();
        CompletableFuture<SendResult<String, Object>> completableFuture = kafkaTemplate.send(topic, event);
        completableFuture.whenComplete((result, e) -> {
            if (e == null) {
                log.info("Published event: {}\nPartition: {}. Offset: {}",
                    event, result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
            } else {
                log.error("Failed to publish event: {}", event, e);
            }
        });
    }
}
