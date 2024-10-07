package org.atlas.framework.event.core.contract.order.choreography;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.atlas.framework.event.core.EventType;
import org.atlas.framework.event.core.contract.order.BaseOrderEvent;
import org.atlas.order.application.contract.model.OrderDto;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CreditReservedEvent extends BaseOrderEvent {

    public CreditReservedEvent(OrderDto order) {
        this.order = order;
    }

    @Override
    public EventType type() {
        return EventType.CREDIT_RESERVED;
    }

    @Override
    public String toString() {
        return "CreditReservedEvent{" +
            "timestamp=" + timestamp +
            ", eventId='" + eventId + '\'' +
            ", order=" + order +
            '}';
    }
}
