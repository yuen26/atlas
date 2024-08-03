package org.atlas.framework.event.contract.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.atlas.framework.event.contract.DomainEvent;
import org.atlas.framework.event.contract.EventType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductDeletedEvent extends DomainEvent {

    private Integer productId;

    @Override
    public EventType type() {
        return EventType.PRODUCT_DELETED;
    }
}
