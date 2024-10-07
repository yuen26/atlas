package org.atlas.framework.event.core.contract.order;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.atlas.framework.event.core.EventType;
import org.atlas.order.application.contract.model.OrderDto;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OrderCanceledEvent extends BaseOrderEvent {

    public OrderCanceledEvent(OrderDto order) {
        this.order = order;
    }

    @Override
    public EventType type() {
        return EventType.ORDER_CANCELED;
    }

    @Override
    public String toString() {
        return "OrderCanceledEvent{" +
            "timestamp=" + timestamp +
            ", eventId='" + eventId + '\'' +
            ", order=" + order +
            '}';
    }
}
