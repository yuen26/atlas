package org.atlas.order.domain.repository;

import org.atlas.commons.utils.paging.PageDto;
import org.atlas.order.domain.entity.Order;
import org.atlas.order.domain.shared.enums.OrderStatus;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    PageDto<Order> find(FindOrderCondition condition);
    Optional<Order> findById(Integer id);
    void insert(Order order);
    void update(Order order);
    void deleteById(Integer id);
    List<Order> findByStatusAndCreatedBefore(OrderStatus status, Date date);
}
