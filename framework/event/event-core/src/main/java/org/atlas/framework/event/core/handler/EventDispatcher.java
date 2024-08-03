package org.atlas.framework.event.core.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.atlas.framework.event.contract.DomainEvent;
import org.atlas.framework.event.core.exception.EventHandlerNotFoundException;
import org.atlas.framework.event.core.idempotence.EventIdempotenceService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class EventDispatcher {

    private final Map<String, EventHandler<?>> eventHandlerMap;
    private final EventIdempotenceService eventIdempotenceService;

    public <E extends DomainEvent> void dispatch(E event) {
        EventHandler<E> eventHandler = obtainEventHandler(event);
        if (!eventIdempotenceService.isNew(event)) {
            log.error("Event has already been processed: {}", event);
        }
        try {
            eventHandler.handle(event);
        } finally {
            eventIdempotenceService.deleteKey(event);
        }
    }

    @SuppressWarnings("unchecked")
    private <E extends DomainEvent> EventHandler<E> obtainEventHandler(E event) {
        String eventHandlerBeanName = StringUtils.uncapitalize(event.getClass().getSimpleName()) + "Handler";
        EventHandler<?> eventHandler = eventHandlerMap.get(eventHandlerBeanName);
        if (eventHandler == null) {
            throw new EventHandlerNotFoundException("Not found handler for event " + event.getClass());
        }
        return (EventHandler<E>) eventHandler;
    }
}
