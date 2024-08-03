package org.atlas.business.product.application.contract.command;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.atlas.business.product.application.contract.model.ProductDto;
import org.atlas.business.product.domain.shared.enums.ProductStatus;
import org.atlas.framework.command.contract.Command;
import org.atlas.shared.util.paging.PageDto;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ListProductCommand implements Command<PageDto<ProductDto>> {

    private Integer id;

    private String name;

    private Integer categoryId;

    @DecimalMin(value = "0.0")
    private BigDecimal minPrice;

    @DecimalMin(value = "0.0")
    private BigDecimal maxPrice;

    private Boolean inStock;

    private Boolean featured;

    private ProductStatus status;
    
    private Date startCreatedAt;

    private Date endCreatedAt;

    @Min(1)
    private Integer page;

    @Min(1)
    private Integer size;

    private String sort;
}
