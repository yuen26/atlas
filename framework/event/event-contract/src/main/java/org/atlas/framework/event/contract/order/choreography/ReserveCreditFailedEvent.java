package org.atlas.framework.event.contract.order.choreography;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.atlas.framework.event.contract.EventType;
import org.atlas.framework.event.contract.order.BaseOrderEvent;
import org.atlas.framework.event.contract.order.model.OrderData;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReserveCreditFailedEvent extends BaseOrderEvent {

    private String error;

    public ReserveCreditFailedEvent(OrderData order, String error) {
        this.order = order;
        this.error = error;
    }

    @Override
    public EventType type() {
        return EventType.RESERVE_CREDIT_FAILED;
    }
}
