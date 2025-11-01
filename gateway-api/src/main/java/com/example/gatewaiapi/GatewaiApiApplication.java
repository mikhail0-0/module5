package com.example.gatewaiapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class GatewaiApiApplication {
    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("users", r -> r.path("/users").uri("http://localhost:8080"))
                .route("users/{id}", r -> r.path("/users/{id}").uri("http://localhost:8080"))
                .route("mail", r -> r.path("/mail").uri("http://localhost:8081"))
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewaiApiApplication.class, args);
    }

}
