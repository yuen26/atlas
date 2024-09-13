package org.atlas.framework.event.core.publisher;

import org.atlas.framework.event.contract.DomainEvent;

public interface EventPublisher {

    <E extends DomainEvent> void publish(E event);
}
