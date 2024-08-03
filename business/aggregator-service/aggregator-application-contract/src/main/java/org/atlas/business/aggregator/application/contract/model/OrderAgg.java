package org.atlas.business.aggregator.application.contract.model;

import lombok.Data;
import org.atlas.business.order.domain.shared.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrderAgg {

    private Integer id;
    private CustomerAgg customer;
    private BigDecimal amount;
    private OrderStatus status;
    private List<OrderItemAgg> orderItems;
    private Date createdAt;

    public void addOrderItem(OrderItemAgg orderItem) {
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }
        this.orderItems.add(orderItem);
    }
}
