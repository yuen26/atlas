package org.atlas.framework.event.core.handler;

import org.atlas.framework.event.core.DomainEvent;
import org.atlas.framework.event.core.EventType;

public interface EventHandler<E extends DomainEvent> {

    EventType supports();

    void handle(E event);
}
