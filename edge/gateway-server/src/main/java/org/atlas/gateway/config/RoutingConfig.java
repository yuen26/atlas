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
import org.springframework.http.HttpMethod;

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
            // Auth APIs -> allow any role
            .route("auth-server", r -> r.path("/auth-server/**")
                .filters(f -> f.rewritePath("/auth-server/(?<segment>.*)", "/${segment}"))
                .uri("lb://AUTH-SERVER"))
            // List orders -> allow any role
            .route("list-order", r -> r.path("/order-service/api/orders")
                .and()
                .method(HttpMethod.GET)
                .filters(f -> f.rewritePath("/order-service/(?<segment>.*)", "/${segment}"))
                .uri("lb://ORDER-SERVICE"))
            // Get order -> deny any role
            .route("get-order", r -> r.path("/order-service/api/orders/{id}")
                .and()
                .method(HttpMethod.GET)
                .filters(f -> f.setStatus(403)) // Forbid access to this API
                .uri("lb://ORDER-SERVICE"))
            // Get order status -> allow any role
            .route("get-orders-status", r -> r.path("/order-service/api/orders/{id}/status")
                .and()
                .method(HttpMethod.GET)
                .filters(f -> f.rewritePath("/order-service/(?<segment>.*)", "/${segment}"))
                .uri("lb://ORDER-SERVICE"))
            // Place order -> allow only CUSTOMER
            .route("place-order", r -> r.path("/order-service/api/orders/place")
                .and()
                .method(HttpMethod.POST)
                .filters(f -> f.rewritePath("/order-service/(?<segment>.*)", "/${segment}")
                    .filter(jwtAuthGatewayFilterFactory.apply(List.of(Role.CUSTOMER))))
                .uri("lb://ORDER-SERVICE"))
            // Import order -> allow only ADMIN
            .route("import-order", r -> r.path("/order-service/api/orders/import")
                .and().method(HttpMethod.POST)
                .filters(f -> f.rewritePath("/order-service/(?<segment>.*)", "/${segment}")
                    .filter(jwtAuthGatewayFilterFactory.apply(List.of(Role.ADMIN))))
                .uri("lb://ORDER-SERVICE"))
            // Export order -> allow only ADMIN
            .route("export-order", r -> r.path("/order-service/api/orders/export")
                .and()
                .method(HttpMethod.GET)
                .filters(f -> f.rewritePath("/order-service/(?<segment>.*)", "/${segment}")
                    .filter(jwtAuthGatewayFilterFactory.apply(List.of(Role.ADMIN))))
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
            // Support for unsecured endpoints
            config.setDenyEmptyKey(false);
        };
    }
}
