package org.atlas.gateway.config;

import lombok.RequiredArgsConstructor;
import org.atlas.business.user.domain.shared.enums.Role;
import org.atlas.gateway.security.JwtAuthGatewayFilterFactory;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.filter.factory.RequestRateLimiterGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class RoutingConfig {

    private final JwtAuthGatewayFilterFactory jwtAuthGatewayFilterFactory;
    private final RedisRateLimiter redisRateLimiter;
    private final KeyResolver userKeyResolver;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("auth-server", r -> r.path("/auth-server/**")
                .filters(f -> f.rewritePath("/auth-server/(?<segment>.*)", "/${segment}"))
                .uri("lb://AUTH-SERVER"))
            .route("user-service", r -> r.path("/user-service/**")
                .filters(f -> f.rewritePath("/user-service/(?<segment>.*)", "/${segment}")
                    .filter(jwtAuthGatewayFilterFactory.apply(List.of(Role.ADMIN, Role.CUSTOMER)))
                    .requestRateLimiter(requestRateLimiter()))
                .uri("lb://USER-SERVICE"))
            .route("product-service", r -> r.path("/product-service/**")
                .filters(f -> f.rewritePath("/product-service/(?<segment>.*)", "/${segment}")
                    .filter(jwtAuthGatewayFilterFactory.apply(List.of(Role.ADMIN)))
                    .requestRateLimiter(requestRateLimiter()))
                .uri("lb://PRODUCT-SERVICE"))
            .route("order-service", r -> r.path("/order-service/**")
                .filters(f -> f.rewritePath("/order-service/(?<segment>.*)", "/${segment}")
                    .filter(jwtAuthGatewayFilterFactory.apply(List.of(Role.ADMIN, Role.CUSTOMER)))
                    .requestRateLimiter(requestRateLimiter()))
                .uri("lb://ORDER-SERVICE"))
            .build();
    }

    @Bean
    public DiscoveryClientRouteDefinitionLocator discoveryClientRouteDefinitionLocator(
        ReactiveDiscoveryClient discoveryClient, DiscoveryLocatorProperties properties) {
        properties.setLowerCaseServiceId(true);
        return new DiscoveryClientRouteDefinitionLocator(discoveryClient, properties);
    }

    private Consumer<RequestRateLimiterGatewayFilterFactory.Config> requestRateLimiter() {
        return config -> {
            config.setRateLimiter(redisRateLimiter);
            config.setKeyResolver(userKeyResolver);
            // Support for openapi endpoints which allow empty userId
            config.setDenyEmptyKey(false);
        };
    }
}
