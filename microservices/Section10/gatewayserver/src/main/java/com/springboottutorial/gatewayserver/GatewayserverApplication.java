package com.springboottutorial.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

    public static void main(String[] args) {

        SpringApplication.run(GatewayserverApplication.class, args);
    }

    @Bean
    public RouteLocator mybankRouteConfig(RouteLocatorBuilder builder, RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(r -> r.path("/mybank/accounts/**")
                        .filters(f -> f.rewritePath("/mybank/accounts/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Header", "MyBank")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://ACCOUNTS"))
                .route(r -> r.path("/mybank/cards/**")
                        .filters(f -> f.rewritePath("/mybank/cards/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Header", "MyBank")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://CARDS"))
                .route(r -> r.path("/mybank/loans/**")
                        .filters(f -> f.rewritePath("/mybank/loans/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Header", "MyBank")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://LOANS")).build();
    }

}
