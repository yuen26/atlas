package org.atlas.framework.event.core.contract.order.orchestration;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.atlas.framework.event.core.EventType;
import org.atlas.framework.event.core.contract.order.BaseOrderEvent;
import org.atlas.order.application.contract.model.OrderDto;

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