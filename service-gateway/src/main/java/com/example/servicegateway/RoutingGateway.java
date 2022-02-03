package com.example.servicegateway;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RoutingGateway {
    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(30)).build()).build());
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("web-service", r -> r.path("/web-service/**")
                        .filters(f->f.circuitBreaker(config ->config.setName("Circuit-Breaker").setFallbackUri("forward:/web-fallback")))
                        .uri("lb://WEBSERVICE"))
                .route("name-service", r -> r.path("/name-service/**")
                        .filters(f->f.circuitBreaker(config -> config.setName("Circuit-Breaker").setFallbackUri("forward:/name-fallback")))
                        .uri("lb://NAMESERVICE"))
                .route("greeting-service", r -> r.path("/greeting-service/**")
                        .filters(f->f.circuitBreaker(config -> config.setName("Circuit-Breaker").setFallbackUri("forward:/greetings-fallback")))
                        .uri("lb://GREETINGSERVICE"))


                .build();
    }
}
