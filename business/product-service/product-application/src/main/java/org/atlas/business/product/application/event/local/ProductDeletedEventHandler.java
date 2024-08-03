package org.atlas.business.product.application.event.local;

import lombok.RequiredArgsConstructor;
import org.atlas.business.product.infrastructure.contract.search.SearchService;
import org.atlas.framework.event.contract.product.ProductDeletedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductDeletedEventHandler {

    private final @Nullable SearchService searchService;

    @EventListener
    public void handle(ProductDeletedEvent event) {
        if (searchService != null) {
            searchService.deleteIndex(event.getProductId());
        }
    }
}
