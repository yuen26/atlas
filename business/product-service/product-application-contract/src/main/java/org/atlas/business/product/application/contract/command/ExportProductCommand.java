package org.atlas.business.product.application.contract.command;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.atlas.business.product.domain.shared.enums.FileType;
import org.atlas.business.product.domain.shared.enums.ProductStatus;
import org.atlas.framework.command.contract.Command;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ExportProductCommand implements Command<byte[]> {

    private Integer id;

    private String name;

    private Integer categoryId;

    @DecimalMin(value = "0.0")
    private BigDecimal minPrice;

    @DecimalMin(value = "0.0")
    private BigDecimal maxPrice;

    private Boolean inStock;

    private ProductStatus status;

    private Boolean featured;

    private Date startCreatedAt;

    private Date endCreatedAt;

    private String sort;

    @NotNull
    private FileType fileType;
}
