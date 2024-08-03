package org.atlas.framework.event.contract.product.model;

import lombok.Data;
import org.atlas.business.product.domain.shared.enums.ProductStatus;

import java.math.BigDecimal;

@Data
public class ProductData {

    private Integer id;
    private String name;
    private CategoryData category;
    private BigDecimal price;
    private Integer quantity;
    private ProductStatus status;
    private Boolean featured;
}
