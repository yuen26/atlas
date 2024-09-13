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
public class ReserveQuantityFailedEvent extends BaseOrderEvent {

    private String error;

    public ReserveQuantityFailedEvent(OrderDto order, String error) {
        this.order = order;
        this.error = error;
    }

    @Override
    public EventType type() {
        return EventType.RESERVE_QUANTITY_FAILED;
    }

    @Override
    public String toString() {
        return "ReserveQuantityFailedEvent{" +
            "timestamp=" + timestamp +
            ", eventId='" + eventId + '\'' +
            ", order=" + order +
            ", error='" + error + '\'' +
            '}';
    }
}
