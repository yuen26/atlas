package org.atlas.framework.persistence.jpa.order.adapter;

import lombok.RequiredArgsConstructor;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.repository.FindOrderCondition;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.commons.utils.paging.PageDto;
import org.atlas.framework.persistence.jpa.order.entity.JpaOrder;
import org.atlas.framework.persistence.jpa.order.mapper.OrderMapper;
import org.atlas.framework.persistence.jpa.order.repository.CustomJpaOrderRepository;
import org.atlas.framework.persistence.jpa.order.repository.JpaOrderRepository;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaOrderRepositoryAdapter implements OrderRepository {

    private final JpaOrderRepository jpaOrderRepository;
    private final CustomJpaOrderRepository customJpaOrderRepository;

    @Override
    public PageDto<Order> find(FindOrderCondition condition) {
        long totalCount = customJpaOrderRepository.count(condition);
        if (totalCount == 0L) {
            return PageDto.empty();
        }
        List<JpaOrder> jpaOrders = customJpaOrderRepository.find(condition);
        List<Order> orders = jpaOrders.stream()
            .map(OrderMapper::map)
            .toList();
        return PageDto.of(orders, totalCount);
    }

    @Override
    public Optional<Order> findById(Integer id) {
        return jpaOrderRepository.findByIdAndFetch(id)
            .map(OrderMapper::map);
    }

    @Override
    public void insert(Order order) {
        JpaOrder jpaOrder = OrderMapper.map(order, false);
        jpaOrderRepository.insert(jpaOrder);
        order.setId(jpaOrder.getId());
    }

    @Override
    public void update(Order order) {
        JpaOrder jpaOrder = OrderMapper.map(order, true);
        jpaOrderRepository.save(jpaOrder);
    }

    @Override
    public void deleteById(Integer id) {
        jpaOrderRepository.deleteById(id);
    }

    @Override
    public int softDeleteByStatusAndCreatedBefore(OrderStatus status, Date date) {
        return jpaOrderRepository.softDeleteByStatusAndCreatedBefore(status, date);
    }
}
