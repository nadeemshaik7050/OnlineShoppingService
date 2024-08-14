package com.onlineshoppingservice.paymentservice.services;

import com.onlineshoppingservice.paymentservice.dtos.OrderDetailsDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    private RestTemplateBuilder  restTemplateBuilder;

    @Value("${orderServiceUrl}")
    private String orderServiceUrl;

    public OrderService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public OrderDetailsDto getOrderDetails(String orderId) {
        String url = orderServiceUrl+ orderId;

        RestTemplate restTemplate = restTemplateBuilder.build();
        var response = restTemplate.getForObject(url,OrderDetailsDto.class);
        OrderDetailsDto orderDetailsDto=response;

        return orderDetailsDto;
    }
}
