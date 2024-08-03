package org.atlas.business.product.application.event.local;

import lombok.RequiredArgsConstructor;
import org.atlas.business.product.infrastructure.contract.search.SearchService;
import org.atlas.framework.event.contract.product.ProductCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductCreatedEventHandler {

    private final @Nullable SearchService searchService;

    @EventListener
    public void handle(ProductCreatedEvent event) {
        if (searchService != null) {
            searchService.createIndex(event.getProduct());
        }
    }
}
