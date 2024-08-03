package org.atlas.business.product.application.event.local;

import lombok.RequiredArgsConstructor;
import org.atlas.business.product.infrastructure.contract.search.SearchService;
import org.atlas.framework.event.contract.product.ProductUpdatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductUpdatedEventHandler {

    private final @Nullable SearchService searchService;

    @EventListener
    public void handle(ProductUpdatedEvent event) {
        if (searchService != null) {
            searchService.updateIndex(event.getProduct());
        }
    }
}
