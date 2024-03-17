package com.elevatemart.elevatemartapigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElevateMartMicroserviceRoutes {

    @Bean
    public RouteLocator localHostProductRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("product-service", r -> r.path("/api/v1/attribute/**", "/api/v1/category/**", "/api/v1/product/**", "/api/v1/tax/**")
                        .uri("http://localhost:8800"))
                .build();
    }

    @Bean
    public RouteLocator localHostSecurityRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("security-service", r -> r.path("/api/v1/security/**")
                        .uri("http://localhost:8888"))
                .build();
    }

    @Bean
    public RouteLocator localHostCartRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("cart-service", r -> r.path("/api/v1/cart/**")
                        .uri("http://localhost:9800"))
                .build();
    }
}
