package org.atlas.framework.persistence.mybatis.product.adapter;

import lombok.RequiredArgsConstructor;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.entity.OrderItem;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.framework.persistence.mybatis.product.mapper.OrderMapper;
import org.atlas.shared.util.paging.PageDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MyBatisOrderRepositoryAdapter implements OrderRepository {

    private final OrderMapper orderMapper;

    @Override
    public PageDto<Order> find(int page, int size) {
        long totalCount = orderMapper.countAll();
        if (totalCount == 0L) {
            return PageDto.empty();
        }
        List<Order> products = orderMapper.findAll(page, size);
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
}
