package com.onlineshoppingservice.paymentservice.services.paymentgateway;

import com.onlineshoppingservice.paymentservice.dtos.OrderDetailsDto;
import org.springframework.stereotype.Service;

@Service
public class StripePaymentGateway implements PaymentGateway {
    @Override
    public String generatePaymentLink(OrderDetailsDto orderDetailsDto,String email,String phNum) {
        return null;
    }
}
