package org.atlas.business.product.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.business.product.application.contract.command.SearchProductCommand;
import org.atlas.business.product.application.contract.model.ProductDto;
import org.atlas.business.product.domain.entity.Product;
import org.atlas.business.product.infrastructure.contract.search.SearchProductCondition;
import org.atlas.business.product.infrastructure.contract.search.SearchService;
import org.atlas.framework.command.contract.CommandExecutor;
import org.atlas.shared.exception.AppError;
import org.atlas.shared.exception.BusinessException;
import org.atlas.shared.util.ModelMapperUtil;
import org.atlas.shared.util.paging.PageDto;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchProductCommandExecutor implements CommandExecutor<SearchProductCommand, PageDto<ProductDto>> {

    private final @Nullable SearchService searchService;

    @Override
    public PageDto<ProductDto> execute(SearchProductCommand command) {
        if (searchService == null) {
            throw new BusinessException(AppError.UNAVAILABLE_SERVICE);
        }

        command.setPage(command.getPage() - 1);
        SearchProductCondition condition = ModelMapperUtil.map(command, SearchProductCondition.class);
        PageDto<Product> productPage = searchService.searchProduct(condition);
        return productPage.map(product -> ModelMapperUtil.map(product, ProductDto.class));
    }
}
