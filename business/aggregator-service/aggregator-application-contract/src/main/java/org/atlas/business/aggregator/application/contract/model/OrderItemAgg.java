package org.atlas.business.aggregator.application.contract.model;

import lombok.Data;
import org.atlas.business.product.domain.shared.enums.ProductStatus;

import java.math.BigDecimal;

@Data
public class OrderItemAgg {

    private Integer productId;
    private String productName;
    private BigDecimal productPrice;
    private ProductStatus productStatus;
    private Integer quantity;
}
