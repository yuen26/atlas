package org.atlas.framework.event.contract.order.orchestration;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.atlas.framework.event.contract.EventType;
import org.atlas.framework.event.contract.order.BaseOrderEvent;
import org.atlas.framework.event.contract.order.model.OrderData;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReserveQuantityRequestEvent extends BaseOrderEvent {

    public ReserveQuantityRequestEvent(OrderData order) {
        this.order = order;
    }

    @Override
    public EventType type() {
        return EventType.RESERVE_QUANTITY_REQUEST;
    }
}
