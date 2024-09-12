package org.atlas.framework.grpc.server.order;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.atlas.business.order.application.contract.command.ExportOrderCommand;
import org.atlas.business.order.application.contract.command.GetOrderCommand;
import org.atlas.business.order.application.contract.command.GetOrderStatusCommand;
import org.atlas.business.order.application.contract.command.ImportOrderCommand;
import org.atlas.business.order.application.contract.command.ListOrderCommand;
import org.atlas.business.order.application.contract.command.PlaceOrderCommand;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.business.order.application.contract.model.OrderItemDto;
import org.atlas.business.order.domain.shared.enums.FileType;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.business.user.application.contract.model.CustomerDto;
import org.atlas.commons.constant.Constant;
import org.atlas.commons.utils.DateUtil;
import org.atlas.commons.utils.paging.PageDto;
import org.atlas.framework.command.gateway.CommandGateway;
import org.atlas.framework.grpc.protobuf.common.CustomerProto;
import org.atlas.framework.grpc.protobuf.order.ExportOrderRequestProto;
import org.atlas.framework.grpc.protobuf.order.ExportOrderResponseProto;
import org.atlas.framework.grpc.protobuf.order.GetOrderRequestProto;
import org.atlas.framework.grpc.protobuf.order.GetOrderStatusRequestProto;
import org.atlas.framework.grpc.protobuf.order.ImportOrderRequestProto;
import org.atlas.framework.grpc.protobuf.order.ListOrderRequestProto;
import org.atlas.framework.grpc.protobuf.order.OrderItemProto;
import org.atlas.framework.grpc.protobuf.order.OrderPageProto;
import org.atlas.framework.grpc.protobuf.order.OrderProto;
import org.atlas.framework.grpc.protobuf.order.OrderServiceGrpc;
import org.atlas.framework.grpc.protobuf.order.OrderStatusProto;
import org.atlas.framework.grpc.protobuf.order.PlaceOrderRequestProto;
import org.atlas.framework.grpc.protobuf.order.PlaceOrderResponseProto;

import java.math.BigDecimal;

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
            OrderStatus status = commandGateway.send(command);
            OrderStatusProto responseProto = OrderStatusProto.newBuilder()
                .setStatus(status.name())
                .build();
            responseObserver.onNext(responseProto);
            responseObserver.onCompleted();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void placeOrder(PlaceOrderRequestProto requestProto,
                           StreamObserver<PlaceOrderResponseProto> responseObserver) {
        PlaceOrderCommand command = map(requestProto);
        try {
            Integer id = commandGateway.send(command);
            PlaceOrderResponseProto responseProto = PlaceOrderResponseProto.newBuilder()
                .setId(id)
                .build();
            responseObserver.onNext(responseProto);
            responseObserver.onCompleted();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void importOrder(ImportOrderRequestProto requestProto,
                            StreamObserver<Empty> responseObserver) {
        ImportOrderCommand command = map(requestProto);
        try {
            commandGateway.send(command);
            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void exportOrder(ExportOrderRequestProto requestProto,
                            StreamObserver<ExportOrderResponseProto> responseObserver) {
        ExportOrderCommand command = map(requestProto);
        try {
            byte[] fileContent = commandGateway.send(command);
            ExportOrderResponseProto responseProto = ExportOrderResponseProto.parseFrom(fileContent);
            responseObserver.onNext(responseProto);
            responseObserver.onCompleted();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ListOrderCommand map(ListOrderRequestProto requestProto) {
        ListOrderCommand command = new ListOrderCommand();
        command.setId(requestProto.getId());
        command.setCustomerId(requestProto.getCustomerId());
        command.setMinAmount(BigDecimal.valueOf(requestProto.getMinAmount()));
        command.setMaxAmount(BigDecimal.valueOf(requestProto.getMaxAmount()));
        command.setAddress(requestProto.getAddress());
        command.setStatus(OrderStatus.valueOf(requestProto.getStatus()));
        command.setDeleted(requestProto.getDeleted());
        command.setStartCreatedAt(DateUtil.parse(requestProto.getStartCreatedAt(), Constant.DATE_TIME_FORMAT));
        command.setEndCreatedAt(DateUtil.parse(requestProto.getEndCreatedAt(), Constant.DATE_TIME_FORMAT));
        command.setPage(requestProto.getPage());
        command.setSize(requestProto.getSize());
        command.setSort(requestProto.getSort());
        return command;
    }

    private GetOrderCommand map(GetOrderRequestProto requestProto) {
        return new GetOrderCommand(requestProto.getId());
    }

    private GetOrderStatusCommand map(GetOrderStatusRequestProto requestProto) {
        return new GetOrderStatusCommand(requestProto.getId());
    }

    private PlaceOrderCommand map(PlaceOrderRequestProto requestProto) {
        PlaceOrderCommand request = new PlaceOrderCommand();
        requestProto.getOrderItemList().forEach(orderItemProto -> {
            PlaceOrderCommand.OrderItem orderItem = new PlaceOrderCommand.OrderItem();
            orderItem.setProductId(orderItemProto.getProductId());
            orderItem.setQuantity(orderItemProto.getQuantity());
            request.addItem(orderItem);
        });
        return request;
    }

    private ImportOrderCommand map(ImportOrderRequestProto requestProto) {
        return new ImportOrderCommand(
            FileType.of(requestProto.getFileType()),
            requestProto.getFileContent().toByteArray()
        );
    }

    private ExportOrderCommand map(ExportOrderRequestProto requestProto) {
        ExportOrderCommand command = new ExportOrderCommand();
        command.setId(requestProto.getId());
        command.setCustomerId(requestProto.getCustomerId());
        command.setMinAmount(BigDecimal.valueOf(requestProto.getMinAmount()));
        command.setMaxAmount(BigDecimal.valueOf(requestProto.getMaxAmount()));
        command.setAddress(requestProto.getAddress());
        command.setStatus(OrderStatus.valueOf(requestProto.getStatus()));
        command.setDeleted(requestProto.getDeleted());
        command.setStartCreatedAt(DateUtil.parse(requestProto.getStartCreatedAt(), Constant.DATE_TIME_FORMAT));
        command.setEndCreatedAt(DateUtil.parse(requestProto.getEndCreatedAt(), Constant.DATE_TIME_FORMAT));
        command.setSort(requestProto.getSort());
        return command;
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
        builder.setCustomer(map(orderDto.getCustomer()));
        builder.setAmount(orderDto.getAmount().doubleValue());
        builder.setAddress(orderDto.getAddress());
        builder.setStatus(orderDto.getStatus().name());
        builder.setCreatedAt(DateUtil.format(orderDto.getCreatedAt(), Constant.DATE_TIME_FORMAT));
        orderDto.getOrderItems().forEach(orderItemDto -> builder.addOrderItem(map(orderItemDto)));
        return builder.build();
    }

    private CustomerProto map(CustomerDto customerDto) {
        return CustomerProto.newBuilder()
            .setId(customerDto.getId())
            .setUsername(customerDto.getUsername())
            .setEmail(customerDto.getEmail())
            .build();
    }

    private OrderItemProto map(OrderItemDto orderItemDto) {
        return OrderItemProto.newBuilder()
            .setProductId(orderItemDto.getProductId())
            .setProductName(orderItemDto.getProductName())
            .setProductPrice(orderItemDto.getProductPrice().doubleValue())
            .setQuantity(orderItemDto.getQuantity())
            .build();
    }
}
