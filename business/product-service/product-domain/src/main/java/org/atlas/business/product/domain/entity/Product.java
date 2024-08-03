package org.atlas.business.product.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.atlas.business.product.domain.shared.enums.ProductStatus;
import org.atlas.shared.model.AuditableEntity;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Product extends AuditableEntity implements Serializable {

    @EqualsAndHashCode.Include
    private Integer id;
    private String name;
    private Category category;
    private BigDecimal price;
    private Integer quantity;
    private ProductStatus status;
    private Boolean featured;
}
