package org.atlas.business.product.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.business.product.application.contract.command.ListProductCommand;
import org.atlas.business.product.application.contract.model.ProductDto;
import org.atlas.business.product.domain.entity.Product;
import org.atlas.business.product.domain.repository.ProductRepository;
import org.atlas.commons.utils.mapping.ModelMapperUtil;
import org.atlas.framework.command.contract.CommandExecutor;
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
