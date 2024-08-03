package org.atlas.framework.api.client.contract;

import org.atlas.business.aggregator.application.contract.model.OrderAgg;

import java.util.Optional;

public interface IAggregatorServiceClient {

    Optional<OrderAgg> getOrder(Integer id);
}
