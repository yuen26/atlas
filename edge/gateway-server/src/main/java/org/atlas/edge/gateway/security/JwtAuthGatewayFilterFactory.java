package org.atlas.edge.gateway.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.atlas.business.user.domain.shared.enums.Role;
import org.atlas.commons.constant.CustomHeaders;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthGatewayFilterFactory extends AbstractGatewayFilterFactory<List<Role>> {

    private static final List<String> unsecuredEndpoints = Arrays.asList(
        // Your unsecured endpoints here
        "/api/auth/"
    );

    private final TokenService tokenService;

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("roles");
    }

    @Override
    public GatewayFilter apply(List<Role> roles) {
        return (exchange, chain) -> {
            // Ignore unsecured endpoints
            if (isUnsecured(exchange)) {
                return chain.filter(exchange);
            }

            // Verify token
            String authorizationHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (!StringUtils.hasText(authorizationHeader)) {
                log.error("Missing Authorization header");
                return onError(exchange, "Missing Authorization header", HttpStatus.UNAUTHORIZED);
            }
            TokenInfo tokenInfo;
            try {
                tokenInfo = tokenService.verifyToken(authorizationHeader);
            } catch (Exception e) {
                log.error("Failed to verify token: {}", authorizationHeader, e);
                return onError(exchange, "Invalid token", HttpStatus.UNAUTHORIZED);
            }

            // Authorization
            if (CollectionUtils.isNotEmpty(roles)) {
                if (!roles.contains(tokenInfo.getRole())) {
                    log.error("Access denied: {}", authorizationHeader);
                    return onError(exchange, "Access denied", HttpStatus.FORBIDDEN);
                }
            }

            // Relay claims via headers
            ServerWebExchange modifiedExchange = relayClaims(exchange, tokenInfo);
            return chain.filter(modifiedExchange);
        };
    }

    private boolean isUnsecured(ServerWebExchange exchange) {
        String requestPath = exchange.getRequest().getURI().getPath();
        return unsecuredEndpoints.contains(requestPath);
    }

    private ServerWebExchange relayClaims(ServerWebExchange exchange, TokenInfo tokenInfo) {
        ServerHttpRequest modifiedRequest = exchange.getRequest()
                .mutate()
                .headers(headers -> headers.remove(HttpHeaders.AUTHORIZATION))
                .header(CustomHeaders.USER_ID, String.valueOf(tokenInfo.getUserId()))
                .header(CustomHeaders.USER_ROLE, tokenInfo.getRole().name())
                .build();
        return exchange.mutate()
                .request(modifiedRequest)
                .build();
    }

    private Mono<Void> onError(ServerWebExchange exchange, String message, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        DataBuffer buffer = response.bufferFactory().wrap(message.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }
}
