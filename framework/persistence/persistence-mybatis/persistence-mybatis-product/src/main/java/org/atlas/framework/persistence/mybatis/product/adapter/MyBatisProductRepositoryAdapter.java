package org.atlas.framework.persistence.mybatis.product.adapter;

import lombok.RequiredArgsConstructor;
import org.atlas.business.product.domain.entity.Product;
import org.atlas.business.product.domain.repository.FindProductCondition;
import org.atlas.business.product.domain.repository.ProductRepository;
import org.atlas.framework.persistence.mybatis.product.mapper.ProductMapper;
import org.atlas.shared.util.paging.PageDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MyBatisProductRepositoryAdapter implements ProductRepository {

    private final ProductMapper productMapper;

    @Override
    public PageDto<Product> find(FindProductCondition condition) {
        long totalCount = productMapper.count(condition);
        if (totalCount == 0L) {
            return PageDto.empty();
        }
        List<Product> products = productMapper.find(condition);
        return PageDto.of(products, totalCount);
    }

    @Override
    public List<Product> findByIdIn(List<Integer> ids) {
        return productMapper.findByIdIn(ids);
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return Optional.ofNullable(productMapper.findById(id));
    }

    @Override
    public void insert(Product product) {
        productMapper.insert(product);
    }

    @Override
    public void update(Product product) {
        productMapper.update(product);
    }

    @Override
    public int increaseQuantity(Integer id, Integer amount) {
        return productMapper.increaseQuantity(id, amount);
    }

    @Override
    public int decreaseQuantity(Integer id, Integer amount) {
        return productMapper.decreaseQuantity(id, amount);
    }

    @Override
    public void deleteById(Integer id) {
        productMapper.deleteById(id);
    }
}
