package com.elevatemart.elevatemartapigateway.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleConfig {

    @Bean
    public RouteLocator google(RouteLocatorBuilder builder){
        return builder.routes()
                .route("google",r->r.path("/googlesearch")
                .uri("https://www.google.com"))
                .build();
    }
}
