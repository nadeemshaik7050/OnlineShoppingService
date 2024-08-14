package com.onlineshoppingservice.paymentservice.dtos;

import lombok.Builder;
import lombok.Data;

@Data
public class OrderDetailsDto {
    private Long id;
    private String productName;
    private int quantity;
    private Long amount;
    private int discountInPercentage;
}
