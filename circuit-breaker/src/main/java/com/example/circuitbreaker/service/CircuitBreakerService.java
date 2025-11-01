package com.example.circuitbreaker.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CircuitBreakerService {
    private final RestTemplate restTemplate;

    public CircuitBreakerService(){
        this.restTemplate = new RestTemplate();
    }

    @CircuitBreaker(name="myServiceBreaker", fallbackMethod = "fallbackResponse")
    public String callExternalService(){
        String url = "http://localhost:8081/hello";
        return restTemplate.getForObject(url, String.class);
    }

    public String fallbackResponse(Throwable t){
        return "Сервис временно недоступен";
    }
}
