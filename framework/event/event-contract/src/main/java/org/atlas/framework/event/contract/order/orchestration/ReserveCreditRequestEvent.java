package org.atlas.framework.event.contract.order.orchestration;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.framework.event.contract.EventType;
import org.atlas.framework.event.contract.order.BaseOrderEvent;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReserveCreditRequestEvent extends BaseOrderEvent {

    public ReserveCreditRequestEvent(OrderDto order) {
        this.order = order;
    }

    @Override
    public EventType type() {
        return EventType.RESERVE_CREDIT_REQUEST;
    }

    @Override
    public String toString() {
        return "ReserveCreditRequestEvent{" +
            "timestamp=" + timestamp +
            ", eventId='" + eventId + '\'' +
            ", order=" + order +
            '}';
    }
}
