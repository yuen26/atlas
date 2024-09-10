package org.atlas.framework.rest.server.user.controller;

import lombok.RequiredArgsConstructor;
import org.atlas.business.user.application.contract.command.GetUserAuthCommand;
import org.atlas.business.user.application.contract.model.UserAuthDto;
import org.atlas.framework.command.gateway.CommandGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users/auth")
@RequiredArgsConstructor
public class UserAuthController {

    private final CommandGateway commandGateway;

    @GetMapping
    public UserAuthDto getUserAuth(@RequestParam("email") String email) throws Exception {
        GetUserAuthCommand command = new GetUserAuthCommand(email);
        return commandGateway.send(command);
    }
}
