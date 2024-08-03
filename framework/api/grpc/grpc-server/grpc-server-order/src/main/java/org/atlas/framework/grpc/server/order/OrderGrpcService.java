package org.atlas.framework.grpc.server.order;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.atlas.business.order.application.contract.command.CreateOrderCommand;
import org.atlas.business.order.application.contract.command.GetOrderCommand;
import org.atlas.business.order.application.contract.command.GetOrderStatusCommand;
import org.atlas.business.order.application.contract.command.ListOrderCommand;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.business.order.application.contract.model.OrderItemDto;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.framework.command.gateway.CommandGateway;
import org.atlas.framework.grpc.protobuf.order.CreateOrderRequestProto;
import org.atlas.framework.grpc.protobuf.order.GetOrderRequestProto;
import org.atlas.framework.grpc.protobuf.order.GetOrderStatusRequestProto;
import org.atlas.framework.grpc.protobuf.order.ListOrderRequestProto;
import org.atlas.framework.grpc.protobuf.order.OrderItemProto;
import org.atlas.framework.grpc.protobuf.order.OrderPageProto;
import org.atlas.framework.grpc.protobuf.order.OrderProto;
import org.atlas.framework.grpc.protobuf.order.OrderServiceGrpc;
import org.atlas.framework.grpc.protobuf.order.OrderStatusProto;
import org.atlas.shared.constant.Constant;
import org.atlas.shared.util.DateUtil;
import org.atlas.shared.util.paging.PageDto;

@GrpcService
@RequiredArgsConstructor
public class OrderGrpcService extends OrderServiceGrpc.OrderServiceImplBase {

    private final CommandGateway commandGateway;

    @Override
    public void listOrder(ListOrderRequestProto requestProto,
                          StreamObserver<OrderPageProto> responseObserver) {
        ListOrderCommand command = map(requestProto);
        try {
            PageDto<OrderDto> orderPageDto = commandGateway.send(command);
            OrderPageProto responseProto = map(orderPageDto);
            responseObserver.onNext(responseProto);
            responseObserver.onCompleted();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getOrder(GetOrderRequestProto requestProto,
                         StreamObserver<OrderProto> responseObserver) {
        GetOrderCommand command = map(requestProto);
        try {
            OrderDto orderDto = commandGateway.send(command);
            OrderProto responseProto = map(orderDto);
            responseObserver.onNext(responseProto);
            responseObserver.onCompleted();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getOrderStatus(GetOrderStatusRequestProto requestProto,
                               StreamObserver<OrderStatusProto> responseObserver) {
        GetOrderStatusCommand command = map(requestProto);
        try {
            OrderStatus orderStatus = commandGateway.send(command);
            OrderStatusProto responseProto = map(orderStatus);
            responseObserver.onNext(responseProto);
            responseObserver.onCompleted();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createOrder(CreateOrderRequestProto requestProto,
                            StreamObserver<Empty> responseObserver) {
        CreateOrderCommand command = map(requestProto);
        try {
            commandGateway.send(command);
            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ListOrderCommand map(ListOrderRequestProto requestProto) {
        return new ListOrderCommand(requestProto.getPage(), requestProto.getSize());
    }

    private GetOrderCommand map(GetOrderRequestProto requestProto) {
        return new GetOrderCommand(requestProto.getId());
    }

    private GetOrderStatusCommand map(GetOrderStatusRequestProto requestProto) {
        return new GetOrderStatusCommand(requestProto.getId());
    }

    private CreateOrderCommand map(CreateOrderRequestProto requestProto) {
        CreateOrderCommand request = new CreateOrderCommand();
        requestProto.getOrderItemList().forEach(orderItemProto -> {
            CreateOrderCommand.OrderItem orderItem = new CreateOrderCommand.OrderItem();
            orderItem.setProductId(orderItemProto.getProductId());
            orderItem.setQuantity(orderItemProto.getQuantity());
            request.addItem(orderItem);
        });
        return request;
    }

    private OrderPageProto map(PageDto<OrderDto> orderDtoPage) {
        if (orderDtoPage.isEmpty()) {
            return OrderPageProto.getDefaultInstance();
        }
        OrderPageProto.Builder builder = OrderPageProto.newBuilder();
        orderDtoPage.getRecords().forEach(orderDto -> builder.addOrder(map(orderDto)));
        builder.setTotalCount(orderDtoPage.getTotalCount());
        return builder.build();
    }

    private OrderProto map(OrderDto orderDto) {
        OrderProto.Builder builder = OrderProto.newBuilder();
        builder.setId(orderDto.getId());
        builder.setCustomerId(orderDto.getCustomerId());
        builder.setAmount(orderDto.getAmount().doubleValue());
        builder.setStatus(orderDto.getStatus().name());
        builder.setCreatedAt(DateUtil.format(orderDto.getCreatedAt(), Constant.DATE_TIME_FORMAT));
        orderDto.getOrderItems().forEach(orderItemDto -> builder.addOrderItem(map(orderItemDto)));
        return builder.build();
    }

    private OrderItemProto map(OrderItemDto orderItemDto) {
        return OrderItemProto.newBuilder()
            .setProductId(orderItemDto.getProductId())
            .setProductPrice(orderItemDto.getProductPrice().doubleValue())
            .setQuantity(orderItemDto.getQuantity())
            .build();
    }

    private OrderStatusProto map(OrderStatus status) {
        return OrderStatusProto.newBuilder()
            .setStatus(status.name())
            .build();
    }
}
