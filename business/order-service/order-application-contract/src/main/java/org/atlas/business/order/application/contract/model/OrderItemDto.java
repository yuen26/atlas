package org.atlas.business.order.application.contract.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDto {

    private Integer productId;
    private BigDecimal productPrice;
    private Integer quantity;
}
