package org.atlas.framework.rest.client.feign.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.business.user.application.contract.model.CustomerDto;
import org.atlas.framework.api.client.contract.user.ICustomerServiceClient;
import org.atlas.framework.rest.client.contract.user.ListCustomerResponse;
import org.atlas.framework.rest.client.feign.client.UserFeignClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceClient implements ICustomerServiceClient {

    private final UserFeignClient feignClient;

    @Override
    public List<CustomerDto> listCustomer(List<Integer> ids) {
        ListCustomerResponse response = feignClient.listCustomer(ids);
        return response.getData();
    }
}
