package org.atlas.business.product.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.business.product.application.contract.command.CreateProductCommand;
import org.atlas.business.product.domain.entity.Category;
import org.atlas.business.product.domain.entity.Product;
import org.atlas.business.product.domain.repository.ProductRepository;
import org.atlas.framework.command.contract.CommandExecutor;
import org.atlas.framework.event.contract.product.ProductCreatedEvent;
import org.atlas.framework.event.contract.product.model.ProductData;
import org.atlas.shared.util.ModelMapperUtil;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CreateProductCommandExecutor implements CommandExecutor<CreateProductCommand, Integer> {

    private final ProductRepository productRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @Transactional
    public Integer execute(CreateProductCommand command) {
        Product product = ModelMapperUtil.map(command, Product.class);
        product.setCategory(new Category(command.getCategoryId()));
        productRepository.insert(product);

        ProductCreatedEvent event = newEvent(product);
        applicationEventPublisher.publishEvent(event);

        return product.getId();
    }

    private ProductCreatedEvent newEvent(Product product) {
        ProductData productData = ModelMapperUtil.map(product, ProductData.class);
        return new ProductCreatedEvent(productData);
    }
}
