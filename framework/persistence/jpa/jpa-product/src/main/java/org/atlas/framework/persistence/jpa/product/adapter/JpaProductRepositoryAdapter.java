package org.atlas.framework.persistence.jpa.product.adapter;

import lombok.RequiredArgsConstructor;
import org.atlas.commons.utils.mapping.ModelMapperUtil;
import org.atlas.framework.persistence.jpa.product.repository.JpaProductRepository;
import org.atlas.product.domain.entity.Product;
import org.atlas.product.domain.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaProductRepositoryAdapter implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;

    @Override
    public List<Product> findByIdIn(List<Integer> ids) {
        return jpaProductRepository.findAllById(ids)
            .stream()
            .map(jpaProduct -> ModelMapperUtil.map(jpaProduct, Product.class))
            .toList();
    }

    @Override
    public int increaseQuantity(Integer id, Integer amount) {
        return jpaProductRepository.increaseQuantity(id, amount);
    }

    @Override
    public int decreaseQuantity(Integer id, Integer amount) {
        return jpaProductRepository.decreaseQuantity(id, amount);
    }
}
