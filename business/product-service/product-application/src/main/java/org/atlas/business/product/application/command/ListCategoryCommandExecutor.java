package org.atlas.business.product.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.business.product.application.contract.command.ListCategoryCommand;
import org.atlas.business.product.application.contract.model.CategoryDto;
import org.atlas.business.product.domain.entity.Category;
import org.atlas.business.product.domain.repository.CategoryRepository;
import org.atlas.framework.command.contract.CommandExecutor;
import org.atlas.shared.util.ModelMapperUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListCategoryCommandExecutor implements CommandExecutor<ListCategoryCommand, List<CategoryDto>> {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> execute(ListCategoryCommand command) throws Exception {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
            .map(category -> ModelMapperUtil.map(category, CategoryDto.class))
            .toList();
    }
}
