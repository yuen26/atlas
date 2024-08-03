package org.atlas.business.product.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.business.product.application.contract.command.ListProductByIdsCommand;
import org.atlas.business.product.application.contract.model.ProductDto;
import org.atlas.business.product.domain.entity.Product;
import org.atlas.business.product.domain.repository.ProductRepository;
import org.atlas.framework.command.contract.CommandExecutor;
import org.atlas.shared.util.ModelMapperUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListProductByIdsCommandExecutor implements CommandExecutor<ListProductByIdsCommand, List<ProductDto>> {

    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> execute(ListProductByIdsCommand command) {
        List<Product> products = productRepository.findByIdIn(command.getIds());
        return products.stream()
            .map(product -> ModelMapperUtil.map(product, ProductDto.class))
            .toList();
    }
}
