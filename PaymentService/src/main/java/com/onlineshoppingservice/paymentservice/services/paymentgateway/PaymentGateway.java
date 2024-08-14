package com.onlineshoppingservice.paymentservice.services.paymentgateway;

import com.onlineshoppingservice.paymentservice.dtos.OrderDetailsDto;
import com.razorpay.RazorpayException;

public interface PaymentGateway {

    String generatePaymentLink(OrderDetailsDto orderDetailsDto,String email,String phNum) throws RazorpayException;
}
