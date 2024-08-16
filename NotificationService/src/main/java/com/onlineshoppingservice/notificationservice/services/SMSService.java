package com.onlineshoppingservice.notificationservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlineshoppingservice.notificationservice.dtos.EmailRequestDto;
import com.onlineshoppingservice.notificationservice.dtos.SMSRequestDto;
import com.onlineshoppingservice.notificationservice.util.SMSUtil;
import org.springframework.stereotype.Service;

@Service
public class SMSService {

    private ObjectMapper objectMapper;

    private SMSUtil smsUtil;

    public SMSService(ObjectMapper objectMapper, SMSUtil smsUtil) {
        this.objectMapper = objectMapper;
        this.smsUtil = smsUtil;
    }

    public void sendSMS(String mobile) {
        try {
            SMSRequestDto smsRequestDto = objectMapper.readValue(mobile, SMSRequestDto.class);
            smsUtil.sendSMS(smsRequestDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
