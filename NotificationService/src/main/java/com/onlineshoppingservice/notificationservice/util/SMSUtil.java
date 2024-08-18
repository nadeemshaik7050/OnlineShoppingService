package com.onlineshoppingservice.notificationservice.util;

import com.onlineshoppingservice.notificationservice.dtos.SMSRequestDto;
import com.onlineshoppingservice.notificationservice.models.SMSNotificationDetails;
import com.onlineshoppingservice.notificationservice.repositories.SMSNotificationRepo;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SMSUtil {

    Logger logger = LoggerFactory.getLogger(SMSUtil.class);
    private SMSNotificationRepo smsNotificationRepo;
    private String myPhoneNumber=System.getenv("MY_PHONE_NUMBER");

    private String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    private String AUTH_TOKEN = System.getenv("TWILIO_AUTH");

    public SMSUtil(SMSNotificationRepo smsNotificationRepo) {
        this.smsNotificationRepo = smsNotificationRepo;
    }


    public void sendSMS(SMSRequestDto smsRequestDto) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber(smsRequestDto.getRecipientPhNum()),
                        new com.twilio.type.PhoneNumber(myPhoneNumber),
                        smsRequestDto.getMessage()).create();
        logger.info("SMS Sent Successfully!!");

        SMSNotificationDetails smsNotificationDetails = SMSNotificationDetails.builder()
                .phoneNumber(smsRequestDto.getRecipientPhNum())
                .message(smsRequestDto.getMessage())
                .status("SENT")
                .build();

        smsNotificationRepo.save(smsNotificationDetails);
    }
}
