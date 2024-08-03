package org.atlas.framework.event.contract.order.orchestration;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.atlas.framework.event.contract.EventType;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReserveQuantityReplyEvent extends BaseReplyEvent {

    @Override
    public EventType type() {
        return EventType.RESERVE_QUANTITY_REPLY;
    }
}
