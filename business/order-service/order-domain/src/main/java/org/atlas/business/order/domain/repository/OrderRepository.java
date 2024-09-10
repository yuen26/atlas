package org.atlas.business.order.domain.repository;

import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.commons.utils.paging.PageDto;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    PageDto<Order> find(FindOrderCondition condition);
    Optional<Order> findById(Integer id);
    void insert(Order order);
    void update(Order order);
    void deleteById(Integer id);
    void softDeleteById(Integer id);
    List<Order> findByStatusAndCreatedBefore(OrderStatus status, Date date);
}
