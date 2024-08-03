package org.atlas.framework.rest.server.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.atlas.business.user.application.contract.command.CreateCustomerCommand;
import org.atlas.business.user.application.contract.command.GetLoginUserCommand;
import org.atlas.business.user.application.contract.command.GetUserCommand;
import org.atlas.business.user.application.contract.model.LoginUserDto;
import org.atlas.business.user.application.contract.model.UserDto;
import org.atlas.framework.command.gateway.CommandGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final CommandGateway commandGateway;

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") Integer id) throws Exception {
        GetUserCommand command = new GetUserCommand(id);
        return commandGateway.send(command);
    }

    @GetMapping("/login-user")
    public LoginUserDto getLoginUser(@RequestParam("email") String email) throws Exception {
        GetLoginUserCommand command = new GetLoginUserCommand(email);
        return commandGateway.send(command);
    }

    @PostMapping("/customer")
    public Integer createCustomer(@Valid @RequestBody CreateCustomerCommand command) throws Exception {
        return commandGateway.send(command);
    }
}
