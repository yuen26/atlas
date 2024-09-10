package org.atlas.framework.grpc.client.order;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.business.order.application.contract.model.OrderItemDto;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.business.user.application.contract.model.CustomerDto;
import org.atlas.commons.constant.Constant;
import org.atlas.commons.utils.DateUtil;
import org.atlas.framework.api.client.contract.order.IOrderServiceClient;
import org.atlas.framework.grpc.protobuf.common.CustomerProto;
import org.atlas.framework.grpc.protobuf.order.GetOrderRequestProto;
import org.atlas.framework.grpc.protobuf.order.OrderItemProto;
import org.atlas.framework.grpc.protobuf.order.OrderProto;
import org.atlas.framework.grpc.protobuf.order.OrderServiceGrpc;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class OrderServiceClient implements IOrderServiceClient {

    @GrpcClient("order")
    private OrderServiceGrpc.OrderServiceBlockingStub orderServiceBlockingStub;

    @Override
    public Optional<OrderDto> getOrder(Integer id) {
        GetOrderRequestProto requestProto = map(id);
        OrderProto orderProto = orderServiceBlockingStub.getOrder(requestProto);
        return Optional.of(map(orderProto));
    }

    private GetOrderRequestProto map(Integer id) {
        return GetOrderRequestProto.newBuilder()
            .setId(id)
            .build();
    }

    private OrderDto map(OrderProto orderProto) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(orderProto.getId());
        orderDto.setCustomer(map(orderProto.getCustomer()));
        orderDto.setAmount(BigDecimal.valueOf(orderProto.getAmount()));
        orderDto.setStatus(OrderStatus.valueOf(orderProto.getStatus()));
        orderProto.getOrderItemList().forEach(orderItem -> orderDto.addOrderItem(map(orderItem)));
        orderDto.setCreatedAt(DateUtil.parse(orderProto.getCreatedAt(), Constant.DATE_TIME_FORMAT));
        return orderDto;
    }

    private CustomerDto map(CustomerProto customerProto) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customerProto.getId());
        customerDto.setUsername(customerProto.getUsername());
        customerDto.setEmail(customerProto.getEmail());
        return customerDto;
    }

    private OrderItemDto map(OrderItemProto orderItemProto) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setProductId(orderItemProto.getProductId());
        orderItemDto.setProductPrice(BigDecimal.valueOf(orderItemProto.getProductPrice()));
        orderItemDto.setQuantity(orderItemProto.getQuantity());
        return orderItemDto;
    }
}
