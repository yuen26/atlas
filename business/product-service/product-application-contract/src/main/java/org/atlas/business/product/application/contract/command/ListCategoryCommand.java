package org.atlas.business.product.application.contract.command;

import lombok.Data;
import org.atlas.business.product.application.contract.model.CategoryDto;
import org.atlas.framework.command.contract.Command;

import java.util.List;

@Data
public class ListCategoryCommand implements Command<List<CategoryDto>> {
}
