package com.onlineshoppingservice.notificationservice.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SMSRequestDto {
    private String message;
    private String recipientPhNum;
}
