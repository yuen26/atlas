package org.atlas.business.order.application.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.business.order.application.contract.model.OrderItemDto;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.entity.OrderItem;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.business.product.application.contract.model.ProductDto;
import org.atlas.business.user.application.contract.model.CustomerDto;
import org.atlas.commons.exception.AppError;
import org.atlas.commons.exception.BusinessException;
import org.atlas.framework.api.client.contract.product.IProductServiceClient;
import org.atlas.framework.api.client.contract.user.ICustomerServiceClient;
import org.atlas.framework.event.contract.SagaMode;
import org.atlas.framework.event.contract.order.choreography.OrderCreatedEvent;
import org.atlas.framework.event.contract.order.orchestration.ReserveQuantityRequestEvent;
import org.atlas.framework.event.core.publisher.EventPublisherTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ICustomerServiceClient customerServiceClient;
    private final IProductServiceClient productServiceClient;
    private final EventPublisherTemplate eventPublisherTemplate;

    @Value("${app.saga-mode:orchestration}")
    private String sagaMode;

    public OrderDto aggregate(Order order) {
        List<OrderDto> orderDtoList = aggregate(Collections.singletonList(order));
        return orderDtoList.get(0);
    }

    public List<OrderDto> aggregate(List<Order> orders) {
        Map<Integer, CustomerDto> customerMap = fetchCustomers(orders);
        Map<Integer, ProductDto> productMap = fetchProducts(orders);
        return orders.stream()
            .map(order -> {
                OrderDto orderDto = toOrderDto(order, customerMap, productMap);

                // Calculate order amount
                BigDecimal amount = calculateAmount(orderDto);
                order.setAmount(amount);
                orderDto.setAmount(amount);

                return orderDto;
            })
            .toList();
    }

    public void postCreateOrder(OrderDto order) {
        if (SagaMode.ORCHESTRATION.equals(sagaMode)) {
            ReserveQuantityRequestEvent event = new ReserveQuantityRequestEvent(order);
            eventPublisherTemplate.publish(event);
        } else {
            OrderCreatedEvent event = new OrderCreatedEvent(order);
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

    private Map<Integer, CustomerDto> fetchCustomers(List<Order> orders) {
        List<Integer> customerIds = orders.stream()
            .map(Order::getCustomerId)
            .distinct()
            .toList();
        List<CustomerDto> customers = customerServiceClient.listCustomer(customerIds);
        if (CollectionUtils.isEmpty(customers)) {
            return Collections.emptyMap();
        }
        return customers.stream()
            .collect(Collectors.toMap(CustomerDto::getId, Function.identity()));
    }

    private Map<Integer, ProductDto> fetchProducts(List<Order> orders) {
        List<Integer> productIds = orders.stream()
            .flatMap(order -> order.getOrderItems()
                .stream()
                .map(OrderItem::getProductId))
            .distinct()
            .toList();
        List<ProductDto> products = productServiceClient.listProduct(productIds);

        Map<Integer, ProductDto> productDtoMap = products.stream()
            .collect(Collectors.toMap(ProductDto::getId, Function.identity()));

        // Verify if all product IDs exist
        Set<Integer> productIdsSet = new HashSet<>(productIds);
        Set<Integer> productDtoIdsSet = productDtoMap.keySet();
        if (!productIdsSet.equals(productDtoIdsSet)) {
            throw new BusinessException(AppError.PRODUCT_NOT_FOUND);
        }

        return productDtoMap;
    }

    public static OrderDto toOrderDto(
        Order order, Map<Integer, CustomerDto> customerMap, Map<Integer, ProductDto> productMap
    ) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setAmount(order.getAmount());
        orderDto.setAddress(order.getAddress());
        orderDto.setStatus(order.getStatus());
        orderDto.setCreatedAt(order.getCreatedAt());

        // Customer
        CustomerDto customer = customerMap.get(order.getCustomerId());
        if (customer != null) {
            orderDto.setCustomer(customer);
        } else {
            customer = new CustomerDto();
            customer.setId(order.getCustomerId());
            orderDto.setCustomer(customer);
        }

        // Order items
        for (OrderItem orderItem : order.getOrderItems()) {
            OrderItemDto orderItemDto = new OrderItemDto();
            orderItemDto.setProductId(orderItem.getProductId());
            orderItemDto.setQuantity(orderItem.getQuantity());

            // Product info
            ProductDto product = productMap.get(orderItem.getProductId());
            if (product != null) {
                orderItemDto.setProductName(product.getName());

                orderItem.setProductPrice(product.getPrice());
                orderItemDto.setProductPrice(product.getPrice());
            }

            orderDto.addOrderItem(orderItemDto);
        }

        return orderDto;
    }

    private BigDecimal calculateAmount(OrderDto orderDto) {
        BigDecimal amount = BigDecimal.ZERO;
        for (OrderItemDto orderItemDto : orderDto.getOrderItems()) {
            BigDecimal subAmount = orderItemDto.getProductPrice()
                .multiply(BigDecimal.valueOf(orderItemDto.getQuantity()));
            amount = amount.add(subAmount);
        }
        return amount;
    }
}
