package org.atlas.framework.grpc.client;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.business.order.application.contract.model.OrderItemDto;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.framework.api.client.contract.IOrderServiceClient;
import org.atlas.framework.grpc.protobuf.order.GetOrderRequestProto;
import org.atlas.framework.grpc.protobuf.order.OrderItemProto;
import org.atlas.framework.grpc.protobuf.order.OrderProto;
import org.atlas.framework.grpc.protobuf.order.OrderServiceGrpc;
import org.atlas.shared.constant.Constant;
import org.atlas.shared.util.DateUtil;
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
        orderDto.setCustomerId(orderProto.getCustomerId());
        orderDto.setAmount(BigDecimal.valueOf(orderProto.getAmount()));
        orderDto.setStatus(OrderStatus.valueOf(orderProto.getStatus()));
        orderProto.getOrderItemList().forEach(orderItem -> {
            OrderItemDto orderItemDto = map(orderItem);
            orderDto.addOrderItem(orderItemDto);
        });
        orderDto.setCreatedAt(DateUtil.parse(orderProto.getCreatedAt(), Constant.DATE_TIME_FORMAT));
        return orderDto;
    }

    private OrderItemDto map(OrderItemProto orderItemProto) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setProductId(orderItemProto.getProductId());
        orderItemDto.setProductPrice(BigDecimal.valueOf(orderItemProto.getProductPrice()));
        orderItemDto.setQuantity(orderItemProto.getQuantity());
        return orderItemDto;
    }
}
