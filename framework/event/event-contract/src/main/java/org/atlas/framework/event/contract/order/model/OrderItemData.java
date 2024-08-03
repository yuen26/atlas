package org.atlas.framework.event.contract.order.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderItemData implements Serializable {

    private Integer productId;
    private BigDecimal productPrice;
    private Integer quantity;
}
