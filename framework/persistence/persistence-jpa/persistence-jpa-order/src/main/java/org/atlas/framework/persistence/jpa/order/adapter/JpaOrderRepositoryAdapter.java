package org.atlas.framework.persistence.jpa.order.adapter;

import lombok.RequiredArgsConstructor;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.framework.persistence.jpa.order.entity.JpaOrder;
import org.atlas.framework.persistence.jpa.order.mapper.OrderMapper;
import org.atlas.framework.persistence.jpa.order.repository.JpaOrderRepository;
import org.atlas.shared.util.paging.PageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaOrderRepositoryAdapter implements OrderRepository {

    private final JpaOrderRepository jpaOrderRepository;

    @Override
    public PageDto<Order> find(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<JpaOrder> jpaOrderPage = jpaOrderRepository.findAllAndFetch(pageable);
        if (jpaOrderPage.isEmpty()) {
            return PageDto.empty();
        }
        List<JpaOrder> jpaOrders = jpaOrderPage.getContent();
        List<Order> orders = jpaOrders.stream()
            .map(OrderMapper::map)
            .toList();
        return PageDto.of(orders, jpaOrderPage.getTotalElements());
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
    public List<Order> findByStatusAndCreatedBefore(OrderStatus status, Date date) {
        return jpaOrderRepository.findByStatusAndCreatedAtBefore(status, date)
            .stream()
            .map(OrderMapper::map)
            .toList();
    }
}
