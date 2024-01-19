package com.vet.appointment.system.api.gateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class GatewayConfig {

    private final AuthenticationFilter authenticationFilter;

    public GatewayConfig(AuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("register-account", route -> route
                        .path("/api/account")
                        .and().method(HttpMethod.POST)
                        .filters(filter -> filter.filter(authenticationFilter))
                        .uri("http://localhost:8181/"))
                .route("register-pet", route -> route
                        .path("/api/pet")
                        .filters(filter -> filter.filter(authenticationFilter))
                        .uri("http://localhost:8182/"))
                .route("protected-misc", route -> route
                        .path("/api/protected")
                        .uri("http://localhost:8181"))
                .route("anonymous", route -> route
                        .path("/api/authenticate", "/api/any")
                        .uri("http://localhost:8181"))

                .build();
    }
}
