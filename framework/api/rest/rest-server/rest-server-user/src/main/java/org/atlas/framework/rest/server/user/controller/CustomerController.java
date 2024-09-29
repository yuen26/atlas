package org.atlas.framework.rest.server.user.controller;

import lombok.RequiredArgsConstructor;
import org.atlas.business.user.application.contract.command.ListCustomerCommand;
import org.atlas.business.user.application.contract.model.CustomerDto;
import org.atlas.framework.command.gateway.CommandGateway;
import org.atlas.framework.rest.server.core.response.RestResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CommandGateway commandGateway;

    @GetMapping
    public RestResponse<List<CustomerDto>> listCustomer(@RequestParam("ids") List<Integer> ids) throws Exception {
        ListCustomerCommand command = new ListCustomerCommand(ids);
        return RestResponse.success(commandGateway.send(command));
    }
}
