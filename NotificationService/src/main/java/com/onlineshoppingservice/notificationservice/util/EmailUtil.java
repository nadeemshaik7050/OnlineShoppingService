package com.onlineshoppingservice.notificationservice.util;

import com.onlineshoppingservice.notificationservice.dtos.EmailRequestDto;
import com.onlineshoppingservice.notificationservice.models.EmailNotificationDetails;
import com.onlineshoppingservice.notificationservice.repositories.EmailNotificationRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailUtil {

        private JavaMailSender javaMailSender;

        private EmailNotificationRepo emailNotificationRepo;


        private String fromEmail=System.getenv("MY_Email");

        Logger logger = LoggerFactory.getLogger(EmailUtil.class);

        public EmailUtil(JavaMailSender javaMailSender, EmailNotificationRepo emailNotificationRepo) {
            this.javaMailSender = javaMailSender;
            this.emailNotificationRepo = emailNotificationRepo;
        }

        public void sendEmail(EmailRequestDto emailRequestDto) {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(emailRequestDto.getRecipientEmail());
            mailMessage.setSubject(emailRequestDto.getSubject());
            mailMessage.setText(emailRequestDto.getMessage());
            mailMessage.setFrom(fromEmail);
            javaMailSender.send(mailMessage);
            logger.info("Email Sent Successfully!!");
            EmailNotificationDetails emailNotificationDetails = EmailNotificationDetails.builder()
                    .recipientEmail(emailRequestDto.getRecipientEmail())
                    .subject(emailRequestDto.getSubject())
                    .message(emailRequestDto.getMessage())
                    .status("SENT")
                    .build();

            emailNotificationRepo.save(emailNotificationDetails);
        }

}
