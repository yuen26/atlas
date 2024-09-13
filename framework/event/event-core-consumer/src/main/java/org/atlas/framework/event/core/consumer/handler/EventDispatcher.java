package org.atlas.framework.event.core.consumer.handler;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.atlas.framework.event.contract.DomainEvent;
import org.atlas.framework.event.contract.EventType;
import org.atlas.framework.event.core.consumer.exception.EventHandlerNotFoundException;
import org.atlas.framework.event.core.consumer.idempotence.EventIdempotenceService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class EventDispatcher {

    private final List<EventHandler<?>> allEventHandlers;
    private final EventIdempotenceService eventIdempotenceService;

    private Map<EventType, List<EventHandler<?>>> eventHandlerMap;

    @PostConstruct
    public void init() {
        eventHandlerMap = allEventHandlers.stream()
            .collect(Collectors.groupingBy(EventHandler::supports, Collectors.toList()));
    }

    public <E extends DomainEvent> void dispatch(E event) {
        List<EventHandler<E>> eventHandlers = obtainEventHandler(event);
        if (!eventIdempotenceService.isNew(event)) {
            log.error("Event has already been processed: {}", event);
        }
        try {
            eventHandlers.forEach(eventHandler -> {
                try {
                    eventHandler.handle(event);
                } catch (Exception e) {
                    log.error("Failed to handle event: event={}, handler={}",
                        event, eventHandler.getClass().getSimpleName(), e);
                }
            });
        } finally {
            eventIdempotenceService.deleteKey(event);
        }
    }

    @SuppressWarnings("unchecked")
    private <E extends DomainEvent> List<EventHandler<E>> obtainEventHandler(E event) {
        List<EventHandler<?>> eventHandlers = eventHandlerMap.get(event.type());
        if (CollectionUtils.isEmpty(eventHandlers)) {
            throw new EventHandlerNotFoundException("Not found handler for event " + event.getClass());
        }
        return eventHandlers.stream()
            .map(handler -> (EventHandler<E>) handler)
            .toList();
    }
}
