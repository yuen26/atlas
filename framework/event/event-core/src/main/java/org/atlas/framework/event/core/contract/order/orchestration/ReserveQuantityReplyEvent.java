package org.atlas.framework.event.core.contract.order.orchestration;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.atlas.framework.event.core.EventType;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReserveQuantityReplyEvent extends BaseReplyEvent {

    @Override
    public EventType type() {
        return EventType.RESERVE_QUANTITY_REPLY;
    }

    @Override
    public String toString() {
        return "ReserveQuantityReplyEvent{" +
            "timestamp=" + timestamp +
            ", eventId='" + eventId + '\'' +
            ", order=" + order +
            '}';
    }
}
