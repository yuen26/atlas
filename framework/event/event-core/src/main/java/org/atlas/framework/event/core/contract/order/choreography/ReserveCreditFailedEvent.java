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
public class ReserveCreditFailedEvent extends BaseOrderEvent {

    private String error;

    public ReserveCreditFailedEvent(OrderDto order, String error) {
        this.order = order;
        this.error = error;
    }

    @Override
    public EventType type() {
        return EventType.RESERVE_CREDIT_FAILED;
    }

    @Override
    public String toString() {
        return "ReserveCreditFailedEvent{" +
            "timestamp=" + timestamp +
            ", eventId='" + eventId + '\'' +
            ", order=" + order +
            ", error='" + error + '\'' +
            '}';
    }
}
