package org.atlas.framework.event.contract.order;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.framework.event.contract.DomainEvent;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class BaseOrderEvent extends DomainEvent {

    protected OrderDto order;
}
