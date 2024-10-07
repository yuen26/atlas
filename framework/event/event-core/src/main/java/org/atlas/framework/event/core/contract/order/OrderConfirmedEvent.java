package org.atlas.framework.event.core.contract.order;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.atlas.framework.event.core.EventType;
import org.atlas.order.application.contract.model.OrderDto;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OrderConfirmedEvent extends BaseOrderEvent {

    public OrderConfirmedEvent(OrderDto order) {
        this.order = order;
    }

    @Override
    public EventType type() {
        return EventType.ORDER_CONFIRMED;
    }

    @Override
    public String toString() {
        return "OrderConfirmedEvent{" +
            "timestamp=" + timestamp +
            ", eventId='" + eventId + '\'' +
            ", order=" + order +
            '}';
    }
}
