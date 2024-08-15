package com.onlineshoppingservice.paymentservice.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentGatewayResponseDto {
    private String paymentLink;
    private String paymentId;
    private String paymentStatus;
}
