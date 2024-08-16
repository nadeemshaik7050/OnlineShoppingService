package com.onlineshoppingservice.paymentservice.dtos;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class SMSDto {

    private String message;
    private String recipientPhNum;
}
