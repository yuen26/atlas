package org.atlas.business.product.application.contract.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.atlas.framework.command.contract.Command;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteProductCommand implements Command<Void> {

    private Integer id;
}
