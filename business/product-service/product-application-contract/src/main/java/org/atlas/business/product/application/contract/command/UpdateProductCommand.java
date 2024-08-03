package org.atlas.business.product.application.contract.command;

import lombok.Data;
import org.atlas.business.product.domain.shared.enums.ProductStatus;
import org.atlas.framework.command.contract.Command;

import java.math.BigDecimal;

@Data
public class UpdateProductCommand implements Command<Void> {

    private Integer id;
    private String name;
    private Integer categoryId;
    private BigDecimal price;
    private Integer quantity;
    private ProductStatus status;
    private Boolean featured;
}
