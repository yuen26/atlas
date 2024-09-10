package org.atlas.business.order.application.contract.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.atlas.framework.command.contract.Command;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteOrderCommand implements Command<Void> {

    @NotNull
    private Integer id;
}
