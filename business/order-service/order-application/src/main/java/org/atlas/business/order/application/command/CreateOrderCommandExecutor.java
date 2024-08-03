package org.atlas.business.order.application.command;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.atlas.business.order.application.contract.command.CreateOrderCommand;
import org.atlas.business.order.application.mapper.OrderMapper;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.entity.OrderItem;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.business.product.application.contract.model.ProductDto;
import org.atlas.framework.api.client.contract.IProductServiceClient;
import org.atlas.framework.command.contract.CommandExecutor;
import org.atlas.framework.event.contract.order.choreography.OrderCreatedEvent;
import org.atlas.framework.event.contract.order.model.OrderData;
import org.atlas.framework.event.contract.order.orchestration.ReserveQuantityRequestEvent;
import org.atlas.framework.event.core.publisher.EventPublisherTemplate;
import org.atlas.framework.event.core.saga.SagaMode;
import org.atlas.shared.context.UserContext;
import org.atlas.shared.exception.AppError;
import org.atlas.shared.exception.BusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CreateOrderCommandExecutor implements CommandExecutor<CreateOrderCommand, Integer> {

    private final OrderRepository orderRepository;
    private final IProductServiceClient productServiceClient;
    private final EventPublisherTemplate eventPublisherTemplate;

    @Value("${app.saga-mode:orchestration}")
    private String sagaMode;

    @Override
    @Transactional
    public Integer execute(CreateOrderCommand command) {
        Map<Integer, ProductDto> productDtoMap = fetchProducts(command);
        Order order = newOrder(command, productDtoMap);
        orderRepository.insert(order);

        OrderData orderData = OrderMapper.toOrderData(order);
        if (SagaMode.ORCHESTRATION.equals(sagaMode)) {
            ReserveQuantityRequestEvent event = new ReserveQuantityRequestEvent(orderData);
            eventPublisherTemplate.publish(event);
        } else {
            OrderCreatedEvent event = new OrderCreatedEvent(orderData);
            eventPublisherTemplate.publish(event);
        }

        return order.getId();
    }

    private Map<Integer, ProductDto> fetchProducts(CreateOrderCommand command) {
        List<Integer> productIds = command.getOrderItems()
            .stream()
            .map(CreateOrderCommand.OrderItem::getProductId)
            .distinct()
            .toList();
        List<ProductDto> productDtoList = productServiceClient.listProductByIds(productIds);
        if (CollectionUtils.isEmpty(productDtoList)) {
            throw new BusinessException(AppError.PRODUCT_NOT_FOUND);
        }
        Map<Integer, ProductDto> productDtoMap = productDtoList.stream()
            .collect(Collectors.toMap(ProductDto::getId, Function.identity()));

        // Verify if all product IDs exist
        Set<Integer> productIdsSet = new HashSet<>(productIds);
        Set<Integer> productDtoIdsSet = productDtoMap.keySet();
        if (!productIdsSet.equals(productDtoIdsSet)) {
            throw new BusinessException(AppError.PRODUCT_NOT_FOUND);
        }

        return productDtoMap;
    }

    private Order newOrder(CreateOrderCommand request, Map<Integer, ProductDto> productDtoMap) {
        Order order = new Order();
        order.setCustomerId(UserContext.getCurrentUser().getUserId());
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(new Date());
        BigDecimal amount = BigDecimal.ZERO;
        for (CreateOrderCommand.OrderItem orderItemRequest : request.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            ProductDto productDto = productDtoMap.get(orderItemRequest.getProductId());
            orderItem.setProductId(orderItemRequest.getProductId());
            orderItem.setProductPrice(productDto.getPrice());
            orderItem.setQuantity(orderItemRequest.getQuantity());
            order.addOrderItem(orderItem);
            amount = amount.add(orderItem.getSubAmount());
        }
        order.setAmount(amount);
        return order;
    }
}
