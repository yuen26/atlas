package org.atlas.framework.persistence.jdbc.order.adapter;

import lombok.RequiredArgsConstructor;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.framework.persistence.jdbc.order.repository.JdbcOrderRepository;
import org.atlas.shared.util.paging.PageDto;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcOrderRepositoryAdapter implements OrderRepository {

    private final JdbcOrderRepository jdbcOrderRepository;

    @Override
    public PageDto<Order> find(int page, int size) {
        long totalCount = jdbcOrderRepository.countAll();
        if (totalCount == 0L) {
            return PageDto.empty();
        }
        List<Order> products = jdbcOrderRepository.findAll(size, page * size);
        return PageDto.of(products, totalCount);
    }

    @Override
    public Optional<Order> findById(Integer id) {
        return jdbcOrderRepository.findById(id);
    }

    @Override
    public void insert(Order order) {
       jdbcOrderRepository.insert(order);
    }

    @Override
    public void update(Order order) {
        jdbcOrderRepository.update(order);
    }

    @Override
    public List<Order> findByStatusAndCreatedBefore(OrderStatus status, Date date) {
        return jdbcOrderRepository.findByStatusAndCreatedBefore(status, date);
    }
}
