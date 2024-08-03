package org.atlas.business.product.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.business.product.application.contract.command.ListProductCommand;
import org.atlas.business.product.application.contract.model.ProductDto;
import org.atlas.business.product.domain.entity.Product;
import org.atlas.business.product.domain.repository.FindProductCondition;
import org.atlas.business.product.domain.repository.ProductRepository;
import org.atlas.framework.command.contract.CommandExecutor;
import org.atlas.shared.util.ModelMapperUtil;
import org.atlas.shared.util.paging.PageDto;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ListProductCommandExecutor implements CommandExecutor<ListProductCommand, PageDto<ProductDto>> {

    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public PageDto<ProductDto> execute(ListProductCommand command) {
        command.setPage(command.getPage() - 1);
        FindProductCondition condition = ModelMapperUtil.map(command, FindProductCondition.class);
        PageDto<Product> productPage = productRepository.find(condition);
        return productPage.map(product -> ModelMapperUtil.map(product, ProductDto.class));
    }
}
