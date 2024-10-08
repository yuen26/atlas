package org.atlas.order.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.atlas.commons.model.AuditableEntity;
import org.atlas.order.domain.shared.enums.OrderStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Order extends AuditableEntity implements Serializable {

    @EqualsAndHashCode.Include
    private Integer id;
    private Integer userId;
    private List<OrderItem> orderItems;
    private BigDecimal amount;
    private String address;
    private OrderStatus status;

    public void addOrderItem(OrderItem orderItem) {
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
}
