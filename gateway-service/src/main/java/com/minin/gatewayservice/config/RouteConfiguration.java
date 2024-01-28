package com.minin.gatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfiguration {

    @Bean
    public RouteLocator routes(
            RouteLocatorBuilder builder,
            CustomGatewayFilter authFilter
    ) {
        return builder.routes()
                .route("core", r -> r.path("/core/**")
                        .filters(f -> f.filter(authFilter.apply(new CustomGatewayFilter.Config())))
                        .uri("http://localhost:8080"))
                .build();
    }

}