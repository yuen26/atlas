package org.atlas.business.order.application.contract.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.framework.command.contract.Command;

@Data
@AllArgsConstructor
public class GetOrderCommand implements Command<OrderDto> {

    private Integer id;
}
