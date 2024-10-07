package org.atlas.product.application.contract.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.atlas.framework.command.Command;
import org.atlas.product.application.contract.model.ProductDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListProductCommand implements Command<List<ProductDto>> {

    private List<Integer> ids;
}
