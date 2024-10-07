package org.atlas.auth.application.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.auth.application.contract.command.LoginCommand;
import org.atlas.auth.application.contract.model.LoginResponse;
import org.atlas.auth.application.core.LoginInfo;
import org.atlas.auth.application.core.TokenService;
import org.atlas.framework.command.executor.CommandExecutor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoginCommandExecutor implements CommandExecutor<LoginCommand, LoginResponse> {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Override
    public LoginResponse execute(LoginCommand command) throws Exception {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            command.getUsername(), command.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(authToken);
            LoginResponse response = toLoginResponse(authentication);
            log.info("Succeeded to login: username={}", command.getUsername());
            return response;
        } catch (AuthenticationException e) {
            log.error("Failed to login: username={}", command.getUsername(), e);
            throw e;
        }
    }

    private LoginResponse toLoginResponse(Authentication authentication) {
        LoginInfo loginInfo = (LoginInfo) authentication.getPrincipal();
        String accessToken = tokenService.issueToken(loginInfo);
        return new LoginResponse(accessToken);
    }
}
