package com.onlineshoppingservice.paymentservice.repository;

import com.onlineshoppingservice.paymentservice.model.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentDetails, Long> {

    PaymentDetails findByPaymentIdInGateway(String paymentId);
}
