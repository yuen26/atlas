package org.atlas.auth.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.atlas.auth.api.model.LoginRequest;
import org.atlas.auth.api.model.LoginResponse;
import org.atlas.auth.security.Contexts;
import org.atlas.auth.security.LoginInfo;
import org.atlas.auth.security.LoginService;
import org.atlas.auth.security.TokenService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final LoginService loginService;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = loginService.login(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public String me() {
        LoginInfo loginInfo = Contexts.loginInfo();
        return loginInfo.toString();
    }

    @GetMapping("/me/v2")
    public String me2(@AuthenticationPrincipal LoginInfo loginInfo) {
        return loginInfo.toString();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        tokenService.revokeToken(authorizationHeader);
        return ResponseEntity.noContent().build();
    }
}
