package com.onlineshoppingservice.notificationservice.repositories;

import com.onlineshoppingservice.notificationservice.models.SMSNotificationDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SMSNotificationRepo extends JpaRepository<SMSNotificationDetails, Long> {
}
