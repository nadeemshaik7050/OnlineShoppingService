package com.onlineshoppingservice.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDto {
    private String email;
    private String phnum;
    private String orderId;

}
