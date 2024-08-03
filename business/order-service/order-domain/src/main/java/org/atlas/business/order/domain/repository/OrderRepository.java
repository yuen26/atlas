package org.atlas.business.order.domain.repository;

import org.atlas.business.order.domain.entity.Order;
import org.atlas.shared.util.paging.PageDto;

import java.util.Optional;

public interface OrderRepository {

    PageDto<Order> find(int page, int size);
    Optional<Order> findById(Integer id);
    void insert(Order order);
    void update(Order order);
}
