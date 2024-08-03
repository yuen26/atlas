package org.atlas.business.order.application.contract.command;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.framework.command.contract.Command;
import org.atlas.shared.util.paging.PageDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListOrderCommand implements Command<PageDto<OrderDto>> {

    @Min(1)
    private Integer page;

    @Min(1)
    private Integer size;
}
