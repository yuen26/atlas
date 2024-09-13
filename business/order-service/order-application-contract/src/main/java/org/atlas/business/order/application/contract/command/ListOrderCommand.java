package org.atlas.business.order.application.contract.command;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.commons.utils.paging.PageDto;
import org.atlas.framework.command.contract.Command;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListOrderCommand implements Command<PageDto<OrderDto>> {

    private Integer id;

    private Integer customerId;

    @DecimalMin(value = "0.0")
    private BigDecimal minAmount;

    @DecimalMin(value = "0.0")
    private BigDecimal maxAmount;

    private String address;

    private OrderStatus status;

    private Date startCreatedAt;

    private Date endCreatedAt;

    @Min(0)
    private Integer page = 0;

    @Min(1)
    private Integer size = 20;

    private String sort;
}
