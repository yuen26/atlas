package org.atlas.business.product.application.contract.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.atlas.business.product.domain.shared.enums.ProductStatus;
import org.atlas.framework.command.contract.Command;

import java.math.BigDecimal;

@Data
public class CreateProductCommand implements Command<Integer> {

    @NotBlank
    private String name;

    @NotNull
    private Integer categoryId;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Integer quantity;

    @NotNull
    private ProductStatus status;

    @NotNull
    private Boolean featured;
}
