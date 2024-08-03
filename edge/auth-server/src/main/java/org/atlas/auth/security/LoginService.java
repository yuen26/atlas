package org.atlas.auth.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.auth.api.model.LoginRequest;
import org.atlas.auth.api.model.LoginResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Transactional
    public LoginResponse login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(authToken);
            LoginResponse response = toLoginResponse(authentication);
            log.info("Succeeded to login: username={}", request.getUsername());
            return response;
        } catch (AuthenticationException e) {
            log.error("Failed to login: username={}", request.getUsername(), e);
            throw e;
        }
    }

    private LoginResponse toLoginResponse(Authentication authentication) {
        LoginInfo loginInfo = (LoginInfo) authentication.getPrincipal();
        String accessToken = tokenService.issueToken(loginInfo);
        return new LoginResponse(accessToken);
    }
}
