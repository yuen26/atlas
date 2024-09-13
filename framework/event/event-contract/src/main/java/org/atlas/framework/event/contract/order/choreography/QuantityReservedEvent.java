package org.atlas.framework.event.contract.order.choreography;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.framework.event.contract.EventType;
import org.atlas.framework.event.contract.order.BaseOrderEvent;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class QuantityReservedEvent extends BaseOrderEvent {

    public QuantityReservedEvent(OrderDto order) {
        this.order = order;
    }

    @Override
    public EventType type() {
        return EventType.QUANTITY_RESERVED;
    }

    @Override
    public String toString() {
        return "QuantityReservedEvent{" +
            "timestamp=" + timestamp +
            ", eventId='" + eventId + '\'' +
            ", order=" + order +
            '}';
    }
}
