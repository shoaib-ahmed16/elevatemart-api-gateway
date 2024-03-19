package com.elevatemart.elevatemartapigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadBalancerRoutes {
    @Bean
    public RouteLocator loadBalancerRoutesLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("security-service", r -> r.path("/api/v1/security/**")
                        .uri("lb://security-micro-service"))
                .route("cart-service", r -> r.path("/api/v1/cart/**")
                        .uri("lb://cart-micro-service"))
                .route("product-service", r -> r.path("/api/v1/attribute/**", "/api/v1/category/**", "/api/v1/product/**", "/api/v1/tax/**")
                        .filters(
                                f->f.circuitBreaker(c->c.setName("productCB")
                                    .setFallbackUri("forward:/product-failOver-micro-service")
                                    .setRouteId("product-failOver-id")
                                ))
                        .uri("lb://product-micro-service"))
                .route("product-failOver-id", r -> r.path("/product-failOver-micro-service/**")
                        .uri("lb://product-failOver-micro-service"))
                .build();
    }
}
