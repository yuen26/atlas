package org.atlas.business.order.application.contract.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.atlas.framework.command.contract.Command;

@Data
public class UpdateOrderCommand implements Command<Void> {

    @NotNull
    private Integer id;

    @NotBlank
    private String address;
}
