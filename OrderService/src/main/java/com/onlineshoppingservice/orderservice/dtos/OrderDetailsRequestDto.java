package com.onlineshoppingservice.orderservice.dtos;

import lombok.Data;

@Data
public class OrderDetailsRequestDto {
    private Long productId;
    private int quantity;
    private int discountinPercentage;
}
