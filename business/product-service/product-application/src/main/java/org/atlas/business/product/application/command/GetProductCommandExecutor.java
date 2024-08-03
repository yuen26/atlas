package org.atlas.business.product.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.business.product.application.contract.command.GetProductCommand;
import org.atlas.business.product.application.contract.model.ProductDto;
import org.atlas.business.product.domain.repository.ProductRepository;
import org.atlas.framework.command.contract.CommandExecutor;
import org.atlas.shared.exception.AppError;
import org.atlas.shared.exception.BusinessException;
import org.atlas.shared.util.ModelMapperUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetProductCommandExecutor implements CommandExecutor<GetProductCommand, ProductDto> {

    private final ProductRepository productRepository;

    @Override
    @Cacheable(cacheNames = "product", key = "#{command.id}")
    public ProductDto execute(GetProductCommand command) throws Exception {
        Integer id = command.getId();
        return productRepository.findById(id)
            .map(product -> ModelMapperUtil.map(product, ProductDto.class))
            .orElseThrow(() -> new BusinessException(AppError.PRODUCT_NOT_FOUND));
    }
}
