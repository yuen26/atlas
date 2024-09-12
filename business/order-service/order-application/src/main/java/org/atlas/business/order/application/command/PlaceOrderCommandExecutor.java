package org.atlas.business.order.application.command;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.atlas.business.order.application.contract.command.PlaceOrderCommand;
import org.atlas.business.order.application.service.OrderService;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.entity.OrderItem;
import org.atlas.business.order.domain.repository.OrderRepository;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.business.product.application.contract.model.ProductDto;
import org.atlas.commons.context.UserContext;
import org.atlas.commons.exception.AppError;
import org.atlas.commons.exception.BusinessException;
import org.atlas.framework.api.client.contract.product.IProductServiceClient;
import org.atlas.framework.command.contract.CommandExecutor;
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
public class PlaceOrderCommandExecutor implements CommandExecutor<PlaceOrderCommand, Integer> {

    private final OrderRepository orderRepository;
    private final IProductServiceClient productServiceClient;
    private final OrderService orderService;

    @Override
    @Transactional
    public Integer execute(PlaceOrderCommand command) {
        Map<Integer, ProductDto> productDtoMap = fetchProducts(command);
        Order order = newOrder(command, productDtoMap);
        orderRepository.insert(order);
        orderService.postCreateOrder(order);
        return order.getId();
    }

    private Map<Integer, ProductDto> fetchProducts(PlaceOrderCommand command) {
        List<Integer> productIds = command.getOrderItems()
            .stream()
            .map(PlaceOrderCommand.OrderItem::getProductId)
            .distinct()
            .toList();
        List<ProductDto> productDtoList = productServiceClient.listProduct(productIds);
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

    private Order newOrder(PlaceOrderCommand request, Map<Integer, ProductDto> productDtoMap) {
        Order order = new Order();
        order.setCustomerId(UserContext.getCurrentUser().getUserId());
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(new Date());
        BigDecimal amount = BigDecimal.ZERO;
        for (PlaceOrderCommand.OrderItem orderItemRequest : request.getOrderItems()) {
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