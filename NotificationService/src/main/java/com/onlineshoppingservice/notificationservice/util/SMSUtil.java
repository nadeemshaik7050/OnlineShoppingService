package com.onlineshoppingservice.notificationservice.util;

import com.onlineshoppingservice.notificationservice.dtos.SMSRequestDto;
import org.springframework.stereotype.Service;

@Service
public class SMSUtil {

    public void sendSMS(SMSRequestDto smsRequestDto) {
        System.out.println("SMS sent to " + smsRequestDto.getRecipientPhNum());
    }
}
