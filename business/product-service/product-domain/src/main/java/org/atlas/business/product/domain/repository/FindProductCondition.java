package org.atlas.business.product.domain.repository;

import lombok.Data;
import org.atlas.business.product.domain.shared.enums.ProductStatus;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class FindProductCondition {

    private Integer id;
    private String name;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Boolean inStock;
    private Boolean featured;
    private ProductStatus status;
    private Integer categoryId;
    private Date startCreatedAt;
    private Date endCreatedAt;
    private Integer page;
    private Integer size;
    private String sort;
}
