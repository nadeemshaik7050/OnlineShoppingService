package com.onlineshoppingservice.orderservice.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionDto {
    private String status;
    private String message;
}
