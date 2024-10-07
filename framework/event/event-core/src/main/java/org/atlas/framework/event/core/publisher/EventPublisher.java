package org.atlas.framework.event.core.publisher;

import org.atlas.framework.event.core.DomainEvent;

public interface EventPublisher {

    <E extends DomainEvent> void publish(E event);
}
