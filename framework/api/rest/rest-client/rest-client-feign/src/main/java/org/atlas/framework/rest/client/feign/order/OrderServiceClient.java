package org.atlas.framework.rest.client.feign.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.framework.api.client.contract.order.IOrderServiceClient;
import org.atlas.framework.rest.client.contract.order.GetOrderResponse;
import org.atlas.framework.rest.client.feign.client.OrderFeignClient;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceClient implements IOrderServiceClient {

    private final OrderFeignClient feignClient;

    @Override
    public Optional<OrderDto> getOrder(Integer id) {
        GetOrderResponse response = feignClient.getOrder(id);
        return Optional.of(response.getData());
    }
}
