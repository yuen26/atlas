package org.atlas.framework.rest.client.feign.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.customer.application.contract.model.CustomerDto;
import org.atlas.framework.api.client.contract.customer.ICustomerServiceClient;
import org.atlas.framework.rest.client.contract.customer.ListCustomerResponse;
import org.atlas.framework.rest.client.feign.client.CustomerFeignClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceClient implements ICustomerServiceClient {

    private final CustomerFeignClient feignClient;

    @Override
    public List<CustomerDto> listCustomer(List<Integer> userIds) {
        ListCustomerResponse response = feignClient.listCustomer(userIds);
        return response.getData();
    }
}
