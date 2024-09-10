package org.atlas.business.order.application.contract.command;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.atlas.business.order.domain.shared.enums.FileType;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.framework.command.contract.Command;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ExportOrderCommand implements Command<byte[]> {

    private Integer id;

    private Integer customerId;

    @DecimalMin(value = "0.0")
    private BigDecimal minAmount;

    @DecimalMin(value = "0.0")
    private BigDecimal maxAmount;

    private String address;

    private OrderStatus status;

    private Boolean deleted;

    private Date startCreatedAt;

    private Date endCreatedAt;

    private String sort;

    @NotNull
    private FileType fileType;
}
