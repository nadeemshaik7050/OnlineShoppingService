package com.onlineshoppingservice.orderservice.service;

import com.onlineshoppingservice.orderservice.dtos.OrderDetailsRequestDto;
import com.onlineshoppingservice.orderservice.dtos.ProductDto;
import com.onlineshoppingservice.orderservice.models.OrderDetails;
import com.onlineshoppingservice.orderservice.repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    private OrderRepo orderRepo;

    @Value("${product.service.url}")
    private String productServiceUrl;
    private RestTemplateBuilder restTemplateBuilder;


    public OrderService(OrderRepo orderRepo,RestTemplateBuilder restTemplateBuilder) {
        this.orderRepo = orderRepo;
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public Long createOrder(OrderDetailsRequestDto orderDetailsRequestDto) {
        String url = productServiceUrl+ orderDetailsRequestDto.getProductId();

        RestTemplate restTemplate = restTemplateBuilder.build();

        var response = restTemplate.getForObject(url,ProductDto.class);

        ProductDto productDto=response;

        long amount = productDto.getPrice() * orderDetailsRequestDto.getQuantity();
        amount = amount-((amount * orderDetailsRequestDto.getDiscountinPercentage())/100);

        OrderDetails orderDetails =OrderDetails.builder()
                .amount(amount)
                .discountInPercentage(orderDetailsRequestDto.getDiscountinPercentage())
                .productName(productDto.getDescription())
                .quantity(orderDetailsRequestDto.getQuantity())
                .build();
        return orderRepo.save(orderDetails).getId();
    }

    public OrderDetails getOrder(Long orderId) {
        return orderRepo.findById(orderId).orElse(null);
    }

}
