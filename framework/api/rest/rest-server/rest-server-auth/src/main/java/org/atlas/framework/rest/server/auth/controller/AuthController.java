package org.atlas.framework.rest.server.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.atlas.auth.application.contract.command.LoginCommand;
import org.atlas.auth.application.contract.command.LogoutCommand;
import org.atlas.auth.application.contract.model.LoginResponse;
import org.atlas.framework.command.gateway.CommandGateway;
import org.atlas.framework.rest.server.core.response.RestResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CommandGateway commandGateway;

    @PostMapping("/login")
    public RestResponse<LoginResponse> login(@Valid @RequestBody LoginCommand command) throws Exception {
        return RestResponse.success(commandGateway.send(command));
    }

    @PostMapping("/logout")
    public RestResponse<Void> logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws Exception {
        LogoutCommand command = new LogoutCommand(authorizationHeader);
        commandGateway.send(command);
        return RestResponse.success();
    }
}
