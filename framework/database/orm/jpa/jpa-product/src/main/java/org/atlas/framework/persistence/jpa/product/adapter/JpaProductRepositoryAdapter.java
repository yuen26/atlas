package org.atlas.framework.persistence.jpa.product.adapter;

import lombok.RequiredArgsConstructor;
import org.atlas.business.product.domain.entity.Product;
import org.atlas.business.product.domain.repository.FindProductCondition;
import org.atlas.business.product.domain.repository.ProductRepository;
import org.atlas.framework.persistence.jpa.product.entity.JpaProduct;
import org.atlas.framework.persistence.jpa.product.repository.CustomJpaProductRepository;
import org.atlas.framework.persistence.jpa.product.repository.JpaProductRepository;
import org.atlas.shared.util.ModelMapperUtil;
import org.atlas.shared.util.paging.PageDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaProductRepositoryAdapter implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;
    private final CustomJpaProductRepository customJpaProductRepository;

    @Override
    public PageDto<Product> find(FindProductCondition condition) {
        long totalCount = customJpaProductRepository.count(condition);
        if (totalCount == 0L) {
            return PageDto.empty();
        }
        List<JpaProduct> jpaProducts = customJpaProductRepository.find(condition);
        List<Product> products = jpaProducts.stream()
            .map(jpaProduct -> ModelMapperUtil.map(jpaProduct, Product.class))
            .toList();
        return PageDto.of(products, totalCount);
    }

    @Override
    public List<Product> findByIdIn(List<Integer> ids) {
        return jpaProductRepository.findAllById(ids)
            .stream()
            .map(jpaProduct -> ModelMapperUtil.map(jpaProduct, Product.class))
            .toList();
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return jpaProductRepository.findById(id)
            .map(jpaProduct -> ModelMapperUtil.map(jpaProduct, Product.class));
    }

    @Override
    public void insert(Product product) {
        JpaProduct jpaProduct = ModelMapperUtil.map(product, JpaProduct.class);
        jpaProductRepository.insert(jpaProduct);
        product.setId(jpaProduct.getId());
    }

    @Override
    public void update(Product product) {
        JpaProduct jpaProduct = ModelMapperUtil.map(product, JpaProduct.class);
        jpaProductRepository.save(jpaProduct);
    }

    @Override
    public int increaseQuantity(Integer id, Integer amount) {
        return jpaProductRepository.increaseQuantity(id, amount);
    }

    @Override
    public int decreaseQuantity(Integer id, Integer amount) {
        return jpaProductRepository.decreaseQuantity(id, amount);
    }

    @Override
    public void deleteById(Integer id) {
        jpaProductRepository.deleteById(id);
    }
}
