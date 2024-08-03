package org.atlas.framework.event.contract.order;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.atlas.framework.event.contract.EventType;
import org.atlas.framework.event.contract.order.model.OrderData;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OrderConfirmedEvent extends BaseOrderEvent {

    public OrderConfirmedEvent(OrderData order) {
        this.order = order;
    }

    @Override
    public EventType type() {
        return EventType.ORDER_CONFIRMED;
    }
}
