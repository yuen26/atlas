package org.atlas.framework.persistence.mybatis.product.adapter;

import lombok.RequiredArgsConstructor;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.entity.OrderItem;
import org.atlas.business.order.domain.repository.FindOrderCondition;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.commons.utils.paging.PageDto;
import org.atlas.framework.persistence.mybatis.product.mapper.OrderMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MyBatisOrderRepositoryAdapter implements OrderRepository {

    private final OrderMapper orderMapper;

    @Override
    public PageDto<Order> find(FindOrderCondition condition) {
        long totalCount = orderMapper.count(condition);
        if (totalCount == 0L) {
            return PageDto.empty();
        }
        List<Order> products = orderMapper.find(condition);
        return PageDto.of(products, totalCount);
    }

    @Override
    public Optional<Order> findById(Integer id) {
        return Optional.ofNullable(orderMapper.findById(id));
    }

    @Override
    public void insert(Order order) {
        // Insert the order and get the generated ID
        orderMapper.insertOrder(order);

        // Insert each order item
        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setOrder(order);
            orderMapper.insertOrderItem(orderItem);
        }
    }

    @Override
    public void update(Order order) {
        orderMapper.update(order);
    }

    @Override
    public void deleteById(Integer id) {
        orderMapper.deleteById(id);
    }

    @Override
    public List<Order> findByStatusAndCreatedBefore(OrderStatus status, Date date) {
        return orderMapper.findByStatusAndCreatedAtBefore(status, date);
    }
}
