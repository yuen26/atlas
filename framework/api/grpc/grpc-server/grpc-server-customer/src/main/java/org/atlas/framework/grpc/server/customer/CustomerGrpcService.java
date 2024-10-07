package org.atlas.framework.grpc.server.customer;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.commons.collections4.CollectionUtils;
import org.atlas.customer.application.contract.command.GetProfileCommand;
import org.atlas.customer.application.contract.command.ListCustomerCommand;
import org.atlas.customer.application.contract.model.CustomerDto;
import org.atlas.framework.command.gateway.CommandGateway;
import org.atlas.framework.grpc.protobuf.common.CustomerProto;
import org.atlas.framework.grpc.protobuf.customer.CustomerListProto;
import org.atlas.framework.grpc.protobuf.customer.CustomerServiceGrpc;
import org.atlas.framework.grpc.protobuf.customer.ListCustomerRequestProto;

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

    @Override
    public void getProfile(Empty request, StreamObserver<CustomerProto> responseObserver) {
        GetProfileCommand command = new GetProfileCommand();
        try {
            CustomerDto customerDto = commandGateway.send(command);
            CustomerProto responseProto = map(customerDto);
            responseObserver.onNext(responseProto);
            responseObserver.onCompleted();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ListCustomerCommand map(ListCustomerRequestProto requestProto) {
        return new ListCustomerCommand(requestProto.getUserIdList());
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
            .setUserId(customerDto.getUserId())
            .setFirstName(customerDto.getFirstName())
            .setLastName(customerDto.getLastName())
            .setEmail(customerDto.getEmail())
            .setPhoneNumber(customerDto.getPhoneNumber())
            .build();
    }
}
