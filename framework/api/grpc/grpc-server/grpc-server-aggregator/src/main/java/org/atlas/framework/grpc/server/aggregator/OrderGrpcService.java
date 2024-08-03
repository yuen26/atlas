package org.atlas.framework.grpc.server.aggregator;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.atlas.business.aggregator.application.contract.command.GetOrderCommand;
import org.atlas.business.aggregator.application.contract.model.CustomerAgg;
import org.atlas.business.aggregator.application.contract.model.OrderAgg;
import org.atlas.business.aggregator.application.contract.model.OrderItemAgg;
import org.atlas.framework.command.gateway.CommandGateway;
import org.atlas.framework.grpc.protobuf.aggregator.CustomerProto;
import org.atlas.framework.grpc.protobuf.aggregator.GetOrderRequestProto;
import org.atlas.framework.grpc.protobuf.aggregator.OrderItemProto;
import org.atlas.framework.grpc.protobuf.aggregator.OrderProto;
import org.atlas.framework.grpc.protobuf.aggregator.OrderServiceGrpc;
import org.atlas.shared.constant.Constant;
import org.atlas.shared.util.DateUtil;

@GrpcService
@RequiredArgsConstructor
public class OrderGrpcService extends OrderServiceGrpc.OrderServiceImplBase {

    private final CommandGateway commandGateway;

    @Override
    public void getOrder(GetOrderRequestProto requestProto,
                         StreamObserver<OrderProto> responseObserver) {
        GetOrderCommand command = map(requestProto);
        try {
            OrderAgg orderAggregationDto = commandGateway.send(command);
            OrderProto responseProto = map(orderAggregationDto);
            responseObserver.onNext(responseProto);
            responseObserver.onCompleted();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private GetOrderCommand map(GetOrderRequestProto requestProto) {
        return new GetOrderCommand(requestProto.getId());
    }

    private OrderProto map(OrderAgg orderAggregationDto) {
        OrderProto.Builder builder = OrderProto.newBuilder();
        builder.setId(orderAggregationDto.getId());
        builder.setCustomer(map(orderAggregationDto.getCustomer()));
        builder.setAmount(orderAggregationDto.getAmount().doubleValue());
        builder.setStatus(orderAggregationDto.getStatus().name());
        builder.setCreatedAt(DateUtil.format(orderAggregationDto.getCreatedAt(), Constant.DATE_TIME_FORMAT));
        orderAggregationDto.getOrderItems().forEach(orderItemAggregationDto ->
            builder.addOrderItem(map(orderItemAggregationDto)));
        return builder.build();
    }

    private CustomerProto map(CustomerAgg customerAgg) {
        return CustomerProto.newBuilder()
            .setId(customerAgg.getId())
            .setUsername(customerAgg.getUsername())
            .setEmail(customerAgg.getEmail())
            .build();
    }

    private OrderItemProto map(OrderItemAgg orderItemAggregationDto) {
        return OrderItemProto.newBuilder()
            .setProductId(orderItemAggregationDto.getProductId())
            .setProductName(orderItemAggregationDto.getProductName())
            .setProductPrice(orderItemAggregationDto.getProductPrice().doubleValue())
            .setProductStatus(orderItemAggregationDto.getProductStatus().name())
            .setQuantity(orderItemAggregationDto.getQuantity())
            .build();
    }
}
