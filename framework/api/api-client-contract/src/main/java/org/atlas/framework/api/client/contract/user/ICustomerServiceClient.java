package org.atlas.framework.api.client.contract.user;

import org.atlas.business.user.application.contract.model.CustomerDto;

import java.util.List;

public interface ICustomerServiceClient {

    List<CustomerDto> listCustomer(List<Integer> ids);
}
