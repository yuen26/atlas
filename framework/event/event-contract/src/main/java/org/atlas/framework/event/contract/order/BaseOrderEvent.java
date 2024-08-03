package org.atlas.framework.event.contract.order;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.atlas.framework.event.contract.DomainEvent;
import org.atlas.framework.event.contract.order.model.OrderData;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class BaseOrderEvent extends DomainEvent {

    protected OrderData order;
}
