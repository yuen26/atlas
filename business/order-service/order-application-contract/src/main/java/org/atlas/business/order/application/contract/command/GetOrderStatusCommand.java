package org.atlas.business.order.application.contract.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.framework.command.contract.Command;

@Data
@AllArgsConstructor
public class GetOrderStatusCommand implements Command<OrderStatus> {

    private Integer id;
}
