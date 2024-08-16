package com.onlineshoppingservice.paymentservice.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmailDto {

    private String subject;
    private String message;
    private String recipientEmail;
}
