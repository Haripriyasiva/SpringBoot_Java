package com.example.gatewayclient.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder){
        return builder.routes().route("serviceone",r -> r.path("/service1/**")
                .uri("lb://serviceone"))
                .route("servicetwo",r -> r.path("/service2/**")
                .uri("lb://servicetwo")).build();

    }

}
