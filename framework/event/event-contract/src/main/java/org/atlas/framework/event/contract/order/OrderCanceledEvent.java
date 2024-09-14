package org.atlas.framework.event.contract.order;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.framework.event.contract.EventType;

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
