package org.atlas.business.product.application.contract.model;

import lombok.Data;
import org.atlas.business.product.domain.shared.enums.ProductStatus;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductDto {

    private Integer id;
    private String name;
    private CategoryDto category;
    private BigDecimal price;
    private Integer quantity;
    private ProductStatus status;
    private Boolean featured;
    private Date createdAt;
    private Date updatedAt;
}
