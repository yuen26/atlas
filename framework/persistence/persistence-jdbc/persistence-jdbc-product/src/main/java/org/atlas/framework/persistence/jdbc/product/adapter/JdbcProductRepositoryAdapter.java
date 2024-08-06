package org.atlas.framework.persistence.jdbc.product.adapter;

import lombok.RequiredArgsConstructor;
import org.atlas.business.product.domain.entity.Product;
import org.atlas.business.product.domain.repository.FindProductCondition;
import org.atlas.business.product.domain.repository.ProductRepository;
import org.atlas.framework.persistence.jdbc.product.repository.JdbcProductRepository;
import org.atlas.shared.util.paging.PageDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcProductRepositoryAdapter implements ProductRepository {

    private final JdbcProductRepository jdbcProductRepository;

    @Override
    public PageDto<Product> find(FindProductCondition condition) {
        long totalCount = jdbcProductRepository.count(condition);
        if (totalCount == 0L) {
            return PageDto.empty();
        }
        List<Product> products = jdbcProductRepository.find(condition);
        return PageDto.of(products, totalCount);
    }

    @Override
    public List<Product> findByIdIn(List<Integer> ids) {
        return jdbcProductRepository.findByIdIn(ids);
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return jdbcProductRepository.findById(id);
    }

    @Override
    public void insert(Product product) {
        jdbcProductRepository.insert(product);
    }

    @Override
    public void update(Product product) {
        jdbcProductRepository.update(product);
    }

    @Override
    public int increaseQuantity(Integer id, Integer amount) {
        return jdbcProductRepository.increaseQuantity(id, amount);
    }

    @Override
    public int decreaseQuantity(Integer id, Integer amount) {
        return jdbcProductRepository.decreaseQuantity(id, amount);
    }

    @Override
    public void deleteById(Integer id) {
        jdbcProductRepository.deleteById(id);
    }
}
