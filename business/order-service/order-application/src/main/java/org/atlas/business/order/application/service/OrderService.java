package org.atlas.business.order.application.service;

import lombok.RequiredArgsConstructor;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.commons.exception.AppError;
import org.atlas.commons.exception.BusinessException;
import org.atlas.framework.event.contract.order.choreography.OrderCreatedEvent;
import org.atlas.framework.event.contract.order.model.OrderData;
import org.atlas.framework.event.contract.order.model.OrderItemData;
import org.atlas.framework.event.contract.order.orchestration.ReserveQuantityRequestEvent;
import org.atlas.framework.event.core.publisher.EventPublisherTemplate;
import org.atlas.framework.event.core.saga.SagaMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final EventPublisherTemplate eventPublisherTemplate;

    @Value("${app.saga-mode:orchestration}")
    private String sagaMode;

    public void postCreateOrder(Order order) {
        OrderData orderData = toOrderData(order);
        if (SagaMode.ORCHESTRATION.equals(sagaMode)) {
            ReserveQuantityRequestEvent event = new ReserveQuantityRequestEvent(orderData);
            eventPublisherTemplate.publish(event);
        } else {
            OrderCreatedEvent event = new OrderCreatedEvent(orderData);
            eventPublisherTemplate.publish(event);
        }
    }

    @Transactional(readOnly = true)
    public Order findPendingOrder(Integer id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new BusinessException(AppError.ORDER_NOT_FOUND));
        if (!OrderStatus.PENDING.equals(order.getStatus())) {
            throw new BusinessException(AppError.ORDER_INVALID_STATUS);
        }
        return order;
    }

    private OrderData toOrderData(Order order) {
        OrderData orderData = new OrderData();
        orderData.setId(order.getId());
        orderData.setCustomerId(order.getCustomerId());
        orderData.setAmount(order.getAmount());
        orderData.setCreatedAt(order.getCreatedAt());
        order.getOrderItems().forEach(orderItemEntity -> {
            OrderItemData orderItemData = new OrderItemData();
            orderItemData.setProductId(orderItemEntity.getProductId());
            orderItemData.setProductPrice(orderItemEntity.getProductPrice());
            orderItemData.setQuantity(orderItemEntity.getQuantity());
            orderData.addOrderItem(orderItemData);
        });
        return orderData;
    }
}
