package org.atlas.framework.api.client.contract;

import org.atlas.business.order.application.contract.model.OrderDto;

import java.util.Optional;

public interface IOrderServiceClient {

    Optional<OrderDto> getOrder(Integer id);
}
