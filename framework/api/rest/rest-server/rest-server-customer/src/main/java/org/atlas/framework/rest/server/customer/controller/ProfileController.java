package org.atlas.framework.rest.server.customer.controller;

import lombok.RequiredArgsConstructor;
import org.atlas.customer.application.contract.command.GetProfileCommand;
import org.atlas.customer.application.contract.model.CustomerDto;
import org.atlas.framework.command.gateway.CommandGateway;
import org.atlas.framework.rest.server.core.response.RestResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final CommandGateway commandGateway;

    @GetMapping
    public RestResponse<CustomerDto> getProfile() throws Exception {
        GetProfileCommand command = new GetProfileCommand();
        return RestResponse.success(commandGateway.send(command));
    }
}
