package com.onlineshoppingservice.notificationservice.listners;

import com.onlineshoppingservice.notificationservice.dtos.Constants;
import com.onlineshoppingservice.notificationservice.dtos.EmailRequestDto;
import com.onlineshoppingservice.notificationservice.services.EmailService;
import com.onlineshoppingservice.notificationservice.services.SMSService;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class NotificationListeners {

    private EmailService emailService;

    private SMSService smsService;

    public NotificationListeners(EmailService emailService, SMSService smsService) {
        this.emailService = emailService;
        this.smsService = smsService;
    }

    @KafkaListener(topics = Constants.Email, groupId = "notification-group")
    public void sendEmail(String email) {
        emailService.sendEmail(email);
    }

    @KafkaListener(topics = Constants.SMS, groupId = "notification-group")
    public void sendSMS(String sms) {
        smsService.sendSMS(sms);
    }
}
