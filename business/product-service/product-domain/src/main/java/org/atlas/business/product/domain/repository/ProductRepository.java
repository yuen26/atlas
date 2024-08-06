package org.atlas.business.product.domain.repository;

import org.atlas.business.product.domain.entity.Product;
import org.atlas.shared.util.paging.PageDto;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    PageDto<Product> find(FindProductCondition query);
    List<Product> findByIdIn(List<Integer> ids);
    Optional<Product> findById(Integer id);
    void insert(Product product);
    void update(Product product);
    int increaseQuantity(Integer id, Integer amount);
    int decreaseQuantity(Integer id, Integer amount);
    void deleteById(Integer id);
}
