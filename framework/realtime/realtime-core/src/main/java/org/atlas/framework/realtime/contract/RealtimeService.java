package org.atlas.framework.realtime.contract;

import org.atlas.framework.event.contract.DomainEvent;

public interface RealtimeService {

    void publish(DomainEvent event);
}
