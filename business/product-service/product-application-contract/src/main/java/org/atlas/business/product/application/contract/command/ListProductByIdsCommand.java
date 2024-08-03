package org.atlas.business.product.application.contract.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.atlas.business.product.application.contract.model.ProductDto;
import org.atlas.framework.command.contract.Command;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListProductByIdsCommand implements Command<List<ProductDto>> {

    private List<Integer> ids;
}
