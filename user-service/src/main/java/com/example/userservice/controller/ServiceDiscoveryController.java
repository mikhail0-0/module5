package com.example.userservice.controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.cloud.client.discovery.DiscoveryClient;

@RestController
public class ServiceDiscoveryController {
    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;

    public ServiceDiscoveryController(DiscoveryClient discoveryClient, RestClient.Builder restClientBuilder) {
        this.discoveryClient = discoveryClient;
        this.restClient = RestClient.builder().build();
    }

    @GetMapping("hello")
    public String getHelloFromNotification() {
        ServiceInstance serviceInstance = discoveryClient.getInstances("notification-service").get(0);
        String notificationResponse = restClient.get()
                .uri(serviceInstance.getUri() + "/hello")
                .retrieve()
                .body(String.class);
        return notificationResponse;
    }
}
