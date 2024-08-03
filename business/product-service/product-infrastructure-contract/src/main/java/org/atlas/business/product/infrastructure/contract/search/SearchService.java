package org.atlas.business.product.infrastructure.contract.search;

import org.atlas.business.product.domain.entity.Product;
import org.atlas.framework.event.contract.product.model.ProductData;
import org.atlas.shared.util.paging.PageDto;

public interface SearchService {

    PageDto<Product> searchProduct(SearchProductCondition condition);
    void createIndex(ProductData product);
    void updateIndex(ProductData product);
    void deleteIndex(Integer productId);
}
