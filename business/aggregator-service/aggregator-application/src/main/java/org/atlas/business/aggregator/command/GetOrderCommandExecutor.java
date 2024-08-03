package org.atlas.business.aggregator.command;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.atlas.business.aggregator.application.contract.command.GetOrderCommand;
import org.atlas.business.aggregator.application.contract.model.CustomerAgg;
import org.atlas.business.aggregator.application.contract.model.OrderAgg;
import org.atlas.business.aggregator.application.contract.model.OrderItemAgg;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.business.order.application.contract.model.OrderItemDto;
import org.atlas.business.product.application.contract.model.ProductDto;
import org.atlas.business.user.application.contract.model.UserDto;
import org.atlas.framework.api.client.contract.IOrderServiceClient;
import org.atlas.framework.api.client.contract.IProductServiceClient;
import org.atlas.framework.api.client.contract.IUserServiceClient;
import org.atlas.framework.command.contract.CommandExecutor;
import org.atlas.shared.exception.AppError;
import org.atlas.shared.exception.BusinessException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetOrderCommandExecutor implements CommandExecutor<GetOrderCommand, OrderAgg> {

    private final IOrderServiceClient orderServiceClient;
    private final IUserServiceClient userServiceClient;
    private final IProductServiceClient productServiceClient;

    @Override
    public OrderAgg execute(GetOrderCommand command) throws Exception {
        // Fetch order
        OrderDto orderDto = orderServiceClient.getOrder(command.getId())
            .orElseThrow(() -> new BusinessException(AppError.ORDER_NOT_FOUND));
        OrderAgg orderAgg = map(orderDto);

        // Fetch customer
        UserDto userDto = userServiceClient.getUser(orderDto.getCustomerId())
            .orElseThrow(() -> new BusinessException(AppError.USER_NOT_FOUND));
        CustomerAgg customerAgg = map(userDto);
        orderAgg.setCustomer(customerAgg);

        // Fetch products
        Map<Integer, ProductDto> productDtoMap = fetchProducts(orderDto);

        orderDto.getOrderItems().forEach(orderItemDto -> {
            ProductDto productDto = productDtoMap.get(orderItemDto.getProductId());
            OrderItemAgg orderItemAgg = map(orderItemDto, productDto);
            orderAgg.addOrderItem(orderItemAgg);
        });

        return orderAgg;
    }

    private OrderAgg map(OrderDto orderDto) {
        OrderAgg orderAggregationDto = new OrderAgg();
        orderAggregationDto.setId(orderDto.getId());
        orderAggregationDto.setAmount(orderDto.getAmount());
        orderAggregationDto.setStatus(orderDto.getStatus());
        orderAggregationDto.setCreatedAt(orderDto.getCreatedAt());
        return orderAggregationDto;
    }

    private CustomerAgg map(UserDto userDto) {
        CustomerAgg customerAgg = new CustomerAgg();
        customerAgg.setId(userDto.getId());
        customerAgg.setUsername(userDto.getUsername());
        customerAgg.setEmail(userDto.getEmail());
        return customerAgg;
    }

    private Map<Integer, ProductDto> fetchProducts(OrderDto orderDto) {
        if (CollectionUtils.isEmpty(orderDto.getOrderItems())) {
            return Collections.emptyMap();
        }

        List<Integer> productIds = orderDto.getOrderItems()
            .stream()
            .filter(Objects::nonNull)
            .map(OrderItemDto::getProductId)
            .distinct()
            .toList();
        if (CollectionUtils.isEmpty(productIds)) {
            return Collections.emptyMap();
        }

        List<ProductDto> products = productServiceClient.listProductByIds(productIds);
        if (CollectionUtils.isEmpty(products)) {
            return Collections.emptyMap();
        }

        return products.stream()
            .collect(Collectors.toMap(ProductDto::getId, Function.identity()));
    }

    private OrderItemAgg map(OrderItemDto orderItemDto, ProductDto productDto) {
        OrderItemAgg orderItemAgg = new OrderItemAgg();
        orderItemAgg.setProductId(orderItemDto.getProductId());
        if (productDto != null) {
            orderItemAgg.setProductName(productDto.getName());
            orderItemAgg.setProductStatus(productDto.getStatus());
        }
        orderItemAgg.setProductPrice(orderItemDto.getProductPrice());
        orderItemAgg.setQuantity(orderItemDto.getQuantity());
        return orderItemAgg;
    }
}
