package org.atlas.order.application.contract.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.atlas.framework.command.Command;
import org.atlas.order.domain.shared.enums.OrderStatus;

@Data
@AllArgsConstructor
public class GetOrderStatusCommand implements Command<OrderStatus> {

    @NotNull
    private Integer id;
}
