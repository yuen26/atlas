package org.atlas.framework.grpc.client.user;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.atlas.business.user.application.contract.model.CustomerDto;
import org.atlas.framework.api.client.contract.user.ICustomerServiceClient;
import org.atlas.framework.grpc.protobuf.common.CustomerProto;
import org.atlas.framework.grpc.protobuf.user.CustomerListProto;
import org.atlas.framework.grpc.protobuf.user.CustomerServiceGrpc;
import org.atlas.framework.grpc.protobuf.user.ListCustomerRequestProto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceClient implements ICustomerServiceClient {

    @GrpcClient("user")
    private CustomerServiceGrpc.CustomerServiceBlockingStub customerServiceBlockingStub;

    @Override
    public List<CustomerDto> listCustomer(List<Integer> ids) {
        ListCustomerRequestProto requestProto = map(ids);
        CustomerListProto responseProto = customerServiceBlockingStub.listCustomer(requestProto);
        return map(responseProto);
    }

    private ListCustomerRequestProto map(List<Integer> ids) {
        return ListCustomerRequestProto.newBuilder()
            .addAllId(ids)
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
        customerDto.setId(customerProto.getId());
        customerDto.setUsername(customerProto.getUsername());
        customerDto.setEmail(customerProto.getEmail());
        return customerDto;
    }
}
