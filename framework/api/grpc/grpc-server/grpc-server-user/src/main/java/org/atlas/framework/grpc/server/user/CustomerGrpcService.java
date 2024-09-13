package org.atlas.framework.grpc.server.user;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.commons.collections4.CollectionUtils;
import org.atlas.business.user.application.contract.command.ListCustomerCommand;
import org.atlas.business.user.application.contract.model.CustomerDto;
import org.atlas.framework.command.gateway.CommandGateway;
import org.atlas.framework.grpc.protobuf.common.CustomerProto;
import org.atlas.framework.grpc.protobuf.user.CustomerListProto;
import org.atlas.framework.grpc.protobuf.user.CustomerServiceGrpc;
import org.atlas.framework.grpc.protobuf.user.ListCustomerRequestProto;

import java.util.List;

@GrpcService
@RequiredArgsConstructor
public class CustomerGrpcService extends CustomerServiceGrpc.CustomerServiceImplBase {

    private final CommandGateway commandGateway;

    @Override
    public void listCustomer(ListCustomerRequestProto requestProto,
                             StreamObserver<CustomerListProto> responseObserver) {
        ListCustomerCommand command = map(requestProto);
        try {
            List<CustomerDto> customerDtoList = commandGateway.send(command);
            CustomerListProto responseProto = map(customerDtoList);
            responseObserver.onNext(responseProto);
            responseObserver.onCompleted();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ListCustomerCommand map(ListCustomerRequestProto requestProto) {
        return new ListCustomerCommand(requestProto.getIdList());
    }

    private CustomerListProto map(List<CustomerDto> customerDtoList) {
        if (CollectionUtils.isEmpty(customerDtoList)) {
            return CustomerListProto.getDefaultInstance();
        }
        CustomerListProto.Builder builder = CustomerListProto.newBuilder();
        customerDtoList.forEach(customerDto -> builder.addCustomer(map(customerDto)));
        return builder.build();
    }

    private CustomerProto map(CustomerDto customerDto) {
        return CustomerProto.newBuilder()
            .setId(customerDto.getId())
            .setUsername(customerDto.getUsername())
            .setEmail(customerDto.getEmail())
            .build();
    }
}
