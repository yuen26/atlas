package org.atlas.framework.event.contract.order.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrderData implements Serializable {

    private Integer orderId;
    private Integer customerId;
    private BigDecimal amount;
    private List<OrderItemData> orderItems;
    private Date createdAt;

    public void addOrderItem(OrderItemData orderItem) {
        if (this.orderItems == null) {
            this.orderItems = new ArrayList<>();
        }
        this.orderItems.add(orderItem);
    }
}
