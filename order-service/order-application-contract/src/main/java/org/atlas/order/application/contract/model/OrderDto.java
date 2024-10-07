package org.atlas.order.application.contract.model;

import lombok.Data;
import org.atlas.customer.application.contract.model.CustomerDto;
import org.atlas.order.domain.shared.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrderDto {

    private Integer id;
    private CustomerDto customer;
    private List<OrderItemDto> orderItems;
    private BigDecimal amount;
    private String address;
    private OrderStatus status;
    private Date createdAt;
    private String canceledReason;

    public void addOrderItem(OrderItemDto orderItem) {
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }
        this.orderItems.add(orderItem);
    }
}
