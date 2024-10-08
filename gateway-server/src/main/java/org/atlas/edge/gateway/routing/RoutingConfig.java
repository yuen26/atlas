package org.atlas.edge.gateway.routing;

import lombok.RequiredArgsConstructor;
import org.atlas.auth.domain.shared.enums.Role;
import org.atlas.edge.gateway.security.JwtAuthGatewayFilterFactory;
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
            .route("auth-service", r -> r.path("/api/auth/**")
                .uri("lb://auth-service"))
            // List order -> allow both of CUSTOMER and ADMIN
            .route("list-order", r -> r.path("/api/orders")
                .and()
                .method(HttpMethod.GET)
                .filters(f -> f.filter(jwtAuthGatewayFilterFactory.apply(List.of(Role.CUSTOMER, Role.ADMIN))))
                .uri("lb://order-service"))
            // Get order (status) -> allow both of CUSTOMER and ADMIN
            .route("get-order", r -> r.path("/api/orders/{id}/**")
                .and()
                .method(HttpMethod.GET)
                .filters(f -> f.filter(jwtAuthGatewayFilterFactory.apply(List.of(Role.CUSTOMER, Role.ADMIN))))
                .uri("lb://order-service"))
            // Place order -> allow only CUSTOMER
            .route("place-order", r -> r.path("/api/orders/place")
                .and()
                .method(HttpMethod.POST)
                .filters(f -> f.filter(jwtAuthGatewayFilterFactory.apply(List.of(Role.CUSTOMER))))
                .uri("lb://order-service"))
            // Import order -> allow only ADMIN
            .route("import-order", r -> r.path("/api/orders/import")
                .and().method(HttpMethod.POST)
                .filters(f -> f.filter(jwtAuthGatewayFilterFactory.apply(List.of(Role.ADMIN))))
                .uri("lb://order-service"))
            // Export order -> allow only ADMIN
            .route("export-order", r -> r.path("/api/orders/export")
                .and()
                .method(HttpMethod.GET)
                .filters(f -> f.filter(jwtAuthGatewayFilterFactory.apply(List.of(Role.ADMIN))))
                .uri("lb://order-service"))
            // Notification -> allow any role
            .route("notification-service", r -> r.path("/notification/**")
                .filters(f -> f.rewritePath("/notification/(?<remaining>.*)", "/${remaining}"))
                .uri("lb://notification-service"))
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
