package org.atlas.business.order.application.aggregator;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.business.order.application.contract.model.OrderItemDto;
import org.atlas.business.order.domain.entity.Order;
import org.atlas.business.order.domain.entity.OrderItem;
import org.atlas.business.product.application.contract.model.ProductDto;
import org.atlas.business.user.application.contract.model.CustomerDto;
import org.atlas.framework.api.client.contract.product.IProductServiceClient;
import org.atlas.framework.api.client.contract.user.ICustomerServiceClient;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderAggregator {

    private final ICustomerServiceClient customerServiceClient;
    private final IProductServiceClient productServiceClient;

    public OrderDto aggregate(Order order) {
        List<OrderDto> orderDtoList = aggregate(Collections.singletonList(order));
        return orderDtoList.get(0);
    }

    public List<OrderDto> aggregate(List<Order> orders) {
        Map<Integer, CustomerDto> customerMap = fetchCustomers(orders);
        Map<Integer, ProductDto> productMap = fetchProducts(orders);
        return orders.stream()
            .map(order -> toOrderDto(order, customerMap, productMap))
            .toList();
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
        if (CollectionUtils.isEmpty(products)) {
            return Collections.emptyMap();
        }
        return products.stream()
            .collect(Collectors.toMap(ProductDto::getId, Function.identity()));
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

        order.getOrderItems().forEach(orderItem -> {
            OrderItemDto orderItemDto = new OrderItemDto();
            orderItemDto.setProductId(orderItem.getProductId());
            orderItemDto.setProductPrice(orderItem.getProductPrice());
            orderItemDto.setQuantity(orderItem.getQuantity());

            // Product
            ProductDto product = productMap.get(orderItem.getProductId());
            if (product != null) {
                orderItemDto.setProductName(product.getName());
            }

            orderDto.addOrderItem(orderItemDto);
        });
        return orderDto;
    }
}
