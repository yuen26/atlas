package org.atlas.framework.rest.server.aggregator.controller;

import lombok.RequiredArgsConstructor;
import org.atlas.business.aggregator.application.contract.command.GetOrderCommand;
import org.atlas.business.aggregator.application.contract.model.OrderAgg;
import org.atlas.framework.command.gateway.CommandGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final CommandGateway commandGateway;

    @GetMapping("/{id}")
    public OrderAgg getOrder(@PathVariable("id") Integer id) throws Exception {
        GetOrderCommand command = new GetOrderCommand(id);
        return commandGateway.send(command);
    }
}
