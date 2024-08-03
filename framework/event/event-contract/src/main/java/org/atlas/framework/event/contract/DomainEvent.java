package org.atlas.framework.event.contract;

import lombok.Data;
import org.atlas.shared.util.IdGenerator;

import java.io.Serializable;
import java.time.Instant;

@Data
public abstract class DomainEvent implements Serializable {

    protected String eventId;
    protected Long timestamp;

    protected DomainEvent() {
        this.eventId = IdGenerator.uuid();
        this.timestamp = Instant.now().toEpochMilli();
    }

    public abstract EventType type();
}
