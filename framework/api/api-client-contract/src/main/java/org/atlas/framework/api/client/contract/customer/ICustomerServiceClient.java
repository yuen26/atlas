package org.atlas.framework.api.client.contract.customer;

import org.atlas.customer.application.contract.model.CustomerDto;

import java.util.List;

public interface ICustomerServiceClient {

    List<CustomerDto> listCustomer(List<Integer> userIds);
}
