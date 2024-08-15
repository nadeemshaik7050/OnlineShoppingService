package com.onlineshoppingservice.paymentservice.services.paymentgateway;

import com.onlineshoppingservice.paymentservice.dtos.OrderDetailsDto;
import com.onlineshoppingservice.paymentservice.dtos.PaymentGatewayResponseDto;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentGateway {

    PaymentGatewayResponseDto generatePaymentLink(OrderDetailsDto orderDetailsDto, String email, String phNum) throws RazorpayException, StripeException;
}
