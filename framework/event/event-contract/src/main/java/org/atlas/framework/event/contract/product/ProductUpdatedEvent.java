package org.atlas.framework.event.contract.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.atlas.framework.event.contract.DomainEvent;
import org.atlas.framework.event.contract.EventType;
import org.atlas.framework.event.contract.product.model.ProductData;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductUpdatedEvent extends DomainEvent {

    private ProductData product;

    @Override
    public EventType type() {
        return EventType.PRODUCT_UPDATED;
    }
}
