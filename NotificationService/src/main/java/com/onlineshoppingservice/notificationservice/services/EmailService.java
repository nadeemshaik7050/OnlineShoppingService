package com.onlineshoppingservice.notificationservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlineshoppingservice.notificationservice.dtos.EmailRequestDto;
import com.onlineshoppingservice.notificationservice.util.EmailUtil;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private ObjectMapper objectMapper;

    private EmailUtil emailUtil;

    public EmailService(ObjectMapper objectMapper, EmailUtil emailUtil) {
        this.objectMapper = objectMapper;
        this.emailUtil = emailUtil;
    }

    public void sendEmail(String email) {
        try {
            EmailRequestDto emailRequestDto = objectMapper.readValue(email, EmailRequestDto.class);
            emailUtil.sendEmail(emailRequestDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
