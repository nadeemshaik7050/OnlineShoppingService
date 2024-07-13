package com.onlineshoppingservice.orderservice.controllers;

import com.onlineshoppingservice.orderservice.dtos.OrderDetailsRequestDto;
import com.onlineshoppingservice.orderservice.exceptions.OrderNotFoundException;
import com.onlineshoppingservice.orderservice.models.OrderDetails;
import com.onlineshoppingservice.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/createOrder")
    public Long createOrder(@RequestBody OrderDetailsRequestDto orderDetails) {
        return orderService.createOrder(orderDetails);
    }

    @GetMapping("/getOrder/{orderId}")
    public OrderDetails getOrder(@PathVariable Long orderId) throws OrderNotFoundException {

        OrderDetails orderDetails=orderService.getOrder(orderId);
        if(orderDetails==null){
            throw new OrderNotFoundException("Order not found");
        }
        return orderDetails;
    }
}
