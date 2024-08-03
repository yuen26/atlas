package org.atlas.framework.event.core.handler;

import org.atlas.framework.event.contract.DomainEvent;

public interface EventHandler<E extends DomainEvent> {

    void handle(E event);
}
