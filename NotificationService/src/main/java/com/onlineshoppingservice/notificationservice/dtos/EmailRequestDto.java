package com.onlineshoppingservice.notificationservice.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailRequestDto {

    private String subject;
    private String message;
    private String recipientEmail;
}
