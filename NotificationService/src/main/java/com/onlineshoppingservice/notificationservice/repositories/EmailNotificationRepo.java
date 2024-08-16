package com.onlineshoppingservice.notificationservice.repositories;

import com.onlineshoppingservice.notificationservice.models.EmailNotificationDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailNotificationRepo extends JpaRepository<EmailNotificationDetails, Long> {
}
