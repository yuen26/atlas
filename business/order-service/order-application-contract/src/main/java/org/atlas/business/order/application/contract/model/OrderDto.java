package org.atlas.business.order.application.contract.model;

import lombok.Data;
import org.atlas.business.order.domain.shared.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrderDto {

    private Integer id;
    private Integer customerId;
    private BigDecimal amount;
    private OrderStatus status;
    private List<OrderItemDto> orderItems;
    private Date createdAt;

    public void addOrderItem(OrderItemDto orderItem) {
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }
        this.orderItems.add(orderItem);
    }
}
