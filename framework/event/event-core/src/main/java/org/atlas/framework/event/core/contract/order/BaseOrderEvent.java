package org.atlas.framework.event.core.contract.order;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.atlas.framework.event.core.DomainEvent;
import org.atlas.order.application.contract.model.OrderDto;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class BaseOrderEvent extends DomainEvent {

    protected OrderDto order;
}
