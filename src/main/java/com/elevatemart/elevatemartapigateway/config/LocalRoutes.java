package com.elevatemart.elevatemartapigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalRoutes {

    @Bean
    public RouteLocator localHostRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("security-service", r -> r.path("/api/v1/security/**")
                        .uri("http://localhost:8888"))
                .route("cart-service", r -> r.path("/api/v1/cart/**")
                        .uri("http://localhost:9800"))
                .route("product-service", r -> r.path("/api/v1/attribute/**", "/api/v1/category/**", "/api/v1/product/**", "/api/v1/tax/**")
                        .filters(f->f.circuitBreaker(c->c.setName("fallBackCB").setFallbackUri("forward:http://localhost:10000").setRouteId("fallback-id")))
                        .uri("http://localhost:8800"))
                .route("product-failOver-id", r -> r.path("/api/v1/product/")
                        .uri("http://localhost:10000"))
                .build();
    }
}
