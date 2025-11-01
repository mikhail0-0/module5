package com.example.circuitbreaker.controller;

import com.example.circuitbreaker.service.CircuitBreakerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircuitBreakerController {
    private final CircuitBreakerService circuitBreakerService;

    public CircuitBreakerController(CircuitBreakerService circuitBreakerService) {
        this.circuitBreakerService = circuitBreakerService;
    }

    @GetMapping("hello")
    public String getHelloWithBreaker() {
        return this.circuitBreakerService.callExternalService();
    }
}
