package org.atlas.framework.persistence.jdbc.product.adapter;

import lombok.RequiredArgsConstructor;
import org.atlas.framework.persistence.jdbc.product.repository.JdbcProductRepository;
import org.atlas.product.domain.entity.Product;
import org.atlas.product.domain.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcProductRepositoryAdapter implements ProductRepository {

    private final JdbcProductRepository jdbcProductRepository;

    @Override
    public List<Product> findByIdIn(List<Integer> ids) {
        return jdbcProductRepository.findByIdIn(ids);
    }

    @Override
    public int increaseQuantity(Integer id, Integer amount) {
        return jdbcProductRepository.increaseQuantity(id, amount);
    }

    @Override
    public int decreaseQuantity(Integer id, Integer amount) {
        return jdbcProductRepository.decreaseQuantity(id, amount);
    }
}
