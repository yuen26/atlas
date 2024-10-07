package org.atlas.framework.grpc.client.customer;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.atlas.customer.application.contract.model.CustomerDto;
import org.atlas.framework.api.client.contract.customer.ICustomerServiceClient;
import org.atlas.framework.grpc.protobuf.common.CustomerProto;
import org.atlas.framework.grpc.protobuf.customer.CustomerListProto;
import org.atlas.framework.grpc.protobuf.customer.CustomerServiceGrpc;
import org.atlas.framework.grpc.protobuf.customer.ListCustomerRequestProto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceClient implements ICustomerServiceClient {

    @GrpcClient("customer")
    private CustomerServiceGrpc.CustomerServiceBlockingStub customerServiceBlockingStub;

    @Override
    public List<CustomerDto> listCustomer(List<Integer> userIds) {
        ListCustomerRequestProto requestProto = map(userIds);
        CustomerListProto responseProto = customerServiceBlockingStub.listCustomer(requestProto);
        return map(responseProto);
    }

    private ListCustomerRequestProto map(List<Integer> userIds) {
        return ListCustomerRequestProto.newBuilder()
            .addAllUserId(userIds)
            .build();
    }

    private List<CustomerDto> map(CustomerListProto customerListProto) {
        return customerListProto.getCustomerList()
            .stream()
            .map(this::map)
            .toList();
    }

    private CustomerDto map(CustomerProto customerProto) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setUserId(customerProto.getUserId());
        customerDto.setFirstName(customerProto.getFirstName());
        customerDto.setLastName(customerProto.getLastName());
        customerDto.setEmail(customerProto.getEmail());
        customerDto.setPhoneNumber(customerProto.getPhoneNumber());
        return customerDto;
    }
}
