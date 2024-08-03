package org.atlas.business.product.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.business.product.application.contract.command.UpdateProductCommand;
import org.atlas.business.product.domain.entity.Category;
import org.atlas.business.product.domain.entity.Product;
import org.atlas.business.product.domain.repository.ProductRepository;
import org.atlas.framework.command.contract.CommandExecutor;
import org.atlas.framework.event.contract.product.ProductUpdatedEvent;
import org.atlas.framework.event.contract.product.model.ProductData;
import org.atlas.shared.exception.AppError;
import org.atlas.shared.exception.BusinessException;
import org.atlas.shared.util.ModelMapperUtil;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class UpdateProductCommandExecutor implements CommandExecutor<UpdateProductCommand, Void> {

    private final ProductRepository productRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @Transactional
    public Void execute(UpdateProductCommand command) {
        Product product = productRepository.findById(command.getId())
            .orElseThrow(() -> new BusinessException(AppError.PRODUCT_NOT_FOUND));
        boolean hasUpdates = false;
        if (StringUtils.hasText(command.getName())) {
            product.setName(command.getName());
            hasUpdates = true;
        }
        if (command.getCategoryId() != null) {
            product.setCategory(new Category(command.getCategoryId()));
            hasUpdates = true;
        }
        if (command.getPrice() != null) {
            product.setPrice(command.getPrice());
            hasUpdates = true;
        }
        if (command.getQuantity() != null) {
            product.setQuantity(command.getQuantity());
            hasUpdates = true;
        }
        if (command.getStatus() != null) {
            product.setStatus(command.getStatus());
            hasUpdates = true;
        }
        if (command.getFeatured() != null) {
            product.setFeatured(command.getFeatured());
            hasUpdates = true;
        }
        if (hasUpdates) {
            productRepository.update(product);

            ProductUpdatedEvent event = newEvent(product);
            applicationEventPublisher.publishEvent(event);
        }
        return null;
    }

    private ProductUpdatedEvent newEvent(Product product) {
        ProductData productData = ModelMapperUtil.map(product, ProductData.class);
        return new ProductUpdatedEvent(productData);
    }
}
