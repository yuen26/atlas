package org.atlas.business.product.application.command;

import lombok.RequiredArgsConstructor;
import org.atlas.business.product.application.contract.command.DeleteProductCommand;
import org.atlas.business.product.domain.entity.Product;
import org.atlas.business.product.domain.repository.ProductRepository;
import org.atlas.framework.command.contract.CommandExecutor;
import org.atlas.framework.event.contract.product.ProductDeletedEvent;
import org.atlas.shared.exception.AppError;
import org.atlas.shared.exception.BusinessException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DeleteProductCommandExecutor implements CommandExecutor<DeleteProductCommand, Void> {

    private final ProductRepository productRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @Transactional
    public Void execute(DeleteProductCommand command) {
        Integer id = command.getId();
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new BusinessException(AppError.PRODUCT_NOT_FOUND));
        productRepository.deleteById(id);

        ProductDeletedEvent event = new ProductDeletedEvent(product.getId());
        applicationEventPublisher.publishEvent(event);

        return null;
    }
}
