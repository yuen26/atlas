package org.atlas.framework.grpc.client;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.atlas.business.aggregator.application.contract.model.CustomerAgg;
import org.atlas.business.aggregator.application.contract.model.OrderAgg;
import org.atlas.business.aggregator.application.contract.model.OrderItemAgg;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.framework.api.client.contract.IAggregatorServiceClient;
import org.atlas.framework.grpc.protobuf.aggregator.CustomerProto;
import org.atlas.framework.grpc.protobuf.aggregator.GetOrderRequestProto;
import org.atlas.framework.grpc.protobuf.aggregator.OrderItemProto;
import org.atlas.framework.grpc.protobuf.aggregator.OrderProto;
import org.atlas.framework.grpc.protobuf.aggregator.OrderServiceGrpc;
import org.atlas.shared.constant.Constant;
import org.atlas.shared.util.DateUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AggregatorServiceClient implements IAggregatorServiceClient {

    @GrpcClient("aggregator")
    private OrderServiceGrpc.OrderServiceBlockingStub orderServiceBlockingStub;

    @Override
    public Optional<OrderAgg> getOrder(Integer id) {
        GetOrderRequestProto requestProto = map(id);
        OrderProto orderProto = orderServiceBlockingStub.getOrder(requestProto);
        return Optional.of(map(orderProto));
    }

    private GetOrderRequestProto map(Integer id) {
        return GetOrderRequestProto.newBuilder()
            .setId(id)
            .build();
    }

    private OrderAgg map(OrderProto orderProto) {
        OrderAgg orderAgg = new OrderAgg();
        orderAgg.setId(orderProto.getId());
        orderAgg.setCustomer(map(orderProto.getCustomer()));
        orderAgg.setAmount(BigDecimal.valueOf(orderProto.getAmount()));
        orderAgg.setStatus(OrderStatus.valueOf(orderProto.getStatus()));
        orderProto.getOrderItemList().forEach(orderItem -> {
            OrderItemAgg orderItemAgg = map(orderItem);
            orderAgg.addOrderItem(orderItemAgg);
        });
        orderAgg.setCreatedAt(DateUtil.parse(orderProto.getCreatedAt(), Constant.DATE_TIME_FORMAT));
        return orderAgg;
    }

    private CustomerAgg map(CustomerProto customerProto) {
        CustomerAgg customerAgg = new CustomerAgg();
        customerAgg.setId(customerProto.getId());
        customerAgg.setUsername(customerProto.getUsername());
        customerAgg.setEmail(customerProto.getEmail());
        return customerAgg;
    }

    private OrderItemAgg map(OrderItemProto orderItemProto) {
        OrderItemAgg orderItemAgg = new OrderItemAgg();
        orderItemAgg.setProductId(orderItemProto.getProductId());
        orderItemAgg.setProductName(orderItemProto.getProductName());
        orderItemAgg.setProductPrice(BigDecimal.valueOf(orderItemProto.getProductPrice()));
        orderItemAgg.setQuantity(orderItemProto.getQuantity());
        return orderItemAgg;
    }
}
