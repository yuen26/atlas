package org.atlas.framework.event.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.atlas.framework.event.core.DomainEvent;
import org.atlas.framework.event.core.handler.EventDispatcher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaEventConsumer {

    private final EventDispatcher eventDispatcher;

    @KafkaListener(
        topics = "#{'${app.event.kafka.consumer.topics}'.split(',')}",
        groupId = "${spring.application.name}",
        containerFactory = "defaultContainerFactory"
    )
    // Non-blocking retry
    @RetryableTopic(
        attempts = "4", // 3 retries
        topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE, // product-retry-0, product-retry-1, product-retry-2, etc.
        backoff = @Backoff(delay = 1000, multiplier = 2, random = true) // Exponential backoff
    )
    public void consume(ConsumerRecord<String, DomainEvent> record) {
        log.info("Consumed record: payload={}, partition={}, offset={}",
            record.value(), record.partition(), record.offset());
        DomainEvent event = record.value();
        eventDispatcher.dispatch(event);
    }
}
