package org.atlas.business.order.application.contract.model;

import lombok.Data;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.business.user.application.contract.model.CustomerDto;

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

    public void addOrderItem(OrderItemDto orderItem) {
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }
        this.orderItems.add(orderItem);
    }
}
