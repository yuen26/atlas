package org.atlas.framework.event.contract.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.atlas.framework.event.contract.DomainEvent;
import org.atlas.framework.event.contract.EventType;
import org.atlas.framework.event.contract.user.model.CustomerData;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CustomerCreatedEvent extends DomainEvent {

    private CustomerData customer;

    @Override
    public EventType type() {
        return EventType.CUSTOMER_CREATED;
    }
}
