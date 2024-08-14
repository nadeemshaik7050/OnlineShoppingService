package com.onlineshoppingservice.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDto {
    private String email;
    private String phnum;
    private String orderId;

}
