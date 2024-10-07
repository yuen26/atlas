package org.atlas.framework.persistence.mybatis.product.adapter;

import lombok.RequiredArgsConstructor;
import org.atlas.framework.persistence.mybatis.product.mapper.ProductMapper;
import org.atlas.product.domain.entity.Product;
import org.atlas.product.domain.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MyBatisProductRepositoryAdapter implements ProductRepository {

    private final ProductMapper productMapper;

    @Override
    public List<Product> findByIdIn(List<Integer> ids) {
        return productMapper.findByIdIn(ids);
    }

    @Override
    public int increaseQuantity(Integer id, Integer amount) {
        return productMapper.increaseQuantity(id, amount);
    }

    @Override
    public int decreaseQuantity(Integer id, Integer amount) {
        return productMapper.decreaseQuantity(id, amount);
    }
}
