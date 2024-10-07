package org.atlas.order.application.contract.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.atlas.framework.command.Command;
import org.atlas.order.application.contract.model.OrderDto;

@Data
@AllArgsConstructor
public class GetOrderCommand implements Command<OrderDto> {

    @NotNull
    private Integer id;
}
