package org.atlas.order.application.contract.command;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.atlas.framework.command.Command;
import org.atlas.order.domain.shared.enums.FileType;
import org.atlas.order.domain.shared.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ExportOrderCommand implements Command<byte[]> {

    private Integer id;

    private Integer userId;

    @DecimalMin(value = "0.0")
    private BigDecimal minAmount;

    @DecimalMin(value = "0.0")
    private BigDecimal maxAmount;

    private String address;

    private OrderStatus status;

    private Date startCreatedAt;

    private Date endCreatedAt;

    private String sort;

    @NotNull
    private FileType fileType;
}
