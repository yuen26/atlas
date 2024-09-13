package org.atlas.framework.event.core.consumer.handler;

import org.atlas.framework.event.contract.DomainEvent;
import org.atlas.framework.event.contract.EventType;

public interface EventHandler<E extends DomainEvent> {

    EventType supports();

    void handle(E event);
}
