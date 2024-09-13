package org.atlas.business.order.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderItem implements Serializable {

    private Order order;
    private Integer productId;
    private BigDecimal productPrice;
    private Integer quantity;
}
