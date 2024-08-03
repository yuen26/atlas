package org.atlas.framework.search.elasticsearch.adapter;

import lombok.RequiredArgsConstructor;
import org.atlas.business.product.domain.entity.Product;
import org.atlas.business.product.infrastructure.contract.search.SearchProductCondition;
import org.atlas.business.product.infrastructure.contract.search.SearchService;
import org.atlas.framework.event.contract.product.model.ProductData;
import org.atlas.framework.search.elasticsearch.entity.EsProduct;
import org.atlas.framework.search.elasticsearch.repository.CustomEsProductRepository;
import org.atlas.framework.search.elasticsearch.repository.EsProductRepository;
import org.atlas.shared.exception.AppError;
import org.atlas.shared.exception.BusinessException;
import org.atlas.shared.util.ModelMapperUtil;
import org.atlas.shared.util.paging.PageDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EsSearchServiceAdapter implements SearchService {

    private final EsProductRepository esProductRepository;
    private final CustomEsProductRepository customEsProductRepository;

    @Override
    public PageDto<Product> searchProduct(SearchProductCondition condition) {
        Page<EsProduct> esProductPage = customEsProductRepository.searchProduct(condition);
        if (esProductPage.isEmpty()) {
            return PageDto.empty();
        }
        List<Product> products = esProductPage.getContent()
            .stream()
            .map(esProduct -> ModelMapperUtil.map(esProduct, Product.class))
            .toList();
        return PageDto.of(products, esProductPage.getTotalElements());
    }

    @Override
    public void createIndex(ProductData product) {
        EsProduct esProduct = ModelMapperUtil.map(product, EsProduct.class);
        esProductRepository.save(esProduct);
    }

    @Override
    public void updateIndex(ProductData product) {
        EsProduct esProduct = esProductRepository.findById(product.getId())
            .orElseThrow(() -> new BusinessException(AppError.PRODUCT_NOT_FOUND));
        ModelMapperUtil.merge(product, esProduct);
        esProductRepository.save(esProduct);
    }

    @Override
    public void deleteIndex(Integer productId) {
        esProductRepository.deleteById(productId);
    }
}
