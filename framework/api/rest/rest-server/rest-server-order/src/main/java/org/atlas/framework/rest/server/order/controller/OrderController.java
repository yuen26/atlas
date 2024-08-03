package org.atlas.framework.rest.server.order.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.atlas.business.order.application.contract.command.CreateOrderCommand;
import org.atlas.business.order.application.contract.command.GetOrderCommand;
import org.atlas.business.order.application.contract.command.GetOrderStatusCommand;
import org.atlas.business.order.application.contract.command.ListOrderCommand;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.framework.command.gateway.CommandGateway;
import org.atlas.shared.util.paging.PageDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final CommandGateway commandGateway;

    @GetMapping("/list")
    public PageDto<OrderDto> listOrder(@Valid ListOrderCommand command) throws Exception {
        return commandGateway.send(command);
    }

    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable("id") Integer id) throws Exception {
        GetOrderCommand command = new GetOrderCommand(id);
        return commandGateway.send(command);
    }

    @GetMapping("/{id}/status")
    public OrderStatus getOrderStatus(@PathVariable("id") Integer id) throws Exception {
        GetOrderStatusCommand command = new GetOrderStatusCommand(id);
        return commandGateway.send(command);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createOrder(@Valid @RequestBody CreateOrderCommand command) throws Exception {
        return commandGateway.send(command);
    }
}
