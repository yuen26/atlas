package org.atlas.business.aggregator.application.contract.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.atlas.business.aggregator.application.contract.model.OrderAgg;
import org.atlas.framework.command.contract.Command;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderCommand implements Command<OrderAgg> {

    private Integer id;
}
