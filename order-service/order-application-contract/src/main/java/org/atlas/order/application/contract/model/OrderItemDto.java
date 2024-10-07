package org.atlas.order.application.contract.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDto {

    private Integer productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer quantity;
}
