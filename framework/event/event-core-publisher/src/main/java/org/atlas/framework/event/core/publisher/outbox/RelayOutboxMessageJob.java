package org.atlas.framework.event.core.publisher.outbox;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.commons.utils.json.JsonUtil;
import org.atlas.framework.event.contract.DomainEvent;
import org.atlas.framework.event.core.publisher.EventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class RelayOutboxMessageJob {

    private final OutboxMessageRepository outboxMessageRepository;
    private final EventPublisher eventPublisher;

    /**
     * Run every minute
     */
    @Scheduled(cron = "0 * * * * *")
    public void run() {
        log.info("Started running {}", this.getClass().getSimpleName());
        List<OutboxMessage> messages = outboxMessageRepository.findAll();
        log.info("Found {} outbox message(s)", messages.size());
        if (messages.isEmpty()) {
            return;
        }
        messages.forEach(message -> {
            DomainEvent event = JsonUtil.toObject(message.getEvent(), DomainEvent.class);
            eventPublisher.publish(event);
            outboxMessageRepository.deleteById(message.getId());
            log.info("Published outbox message: {}", message);
        });
    }
}
