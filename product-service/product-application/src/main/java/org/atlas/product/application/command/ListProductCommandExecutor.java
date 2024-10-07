package org.atlas.product.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.commons.utils.mapping.ModelMapperUtil;
import org.atlas.framework.command.executor.CommandExecutor;
import org.atlas.product.application.contract.command.ListProductCommand;
import org.atlas.product.application.contract.model.ProductDto;
import org.atlas.product.domain.entity.Product;
import org.atlas.product.domain.repository.ProductRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListProductCommandExecutor implements CommandExecutor<ListProductCommand, List<ProductDto>> {

    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> execute(ListProductCommand command) {
        List<Product> products = productRepository.findByIdIn(command.getIds());
        return products.stream()
            .map(product -> ModelMapperUtil.map(product, ProductDto.class))
            .toList();
    }
}
