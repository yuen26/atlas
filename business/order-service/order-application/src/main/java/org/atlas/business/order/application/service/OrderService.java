package org.atlas.business.order.application.service;

import lombok.RequiredArgsConstructor;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.shared.exception.AppError;
import org.atlas.shared.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public Order findPendingOrder(Integer id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new BusinessException(AppError.ORDER_NOT_FOUND));
        if (!OrderStatus.PENDING.equals(order.getStatus())) {
            throw new BusinessException(AppError.ORDER_INVALID_STATUS);
        }
        return order;
    }
}
