package org.atlas.business.order.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.shared.model.AuditableEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Order extends AuditableEntity implements Serializable {

    @EqualsAndHashCode.Include
    private Integer id;
    private Integer customerId;
    private BigDecimal amount;
    private OrderStatus status;
    private List<OrderItem> orderItems;
    private String canceledReason;

    public void addOrderItem(OrderItem orderItem) {
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
}
