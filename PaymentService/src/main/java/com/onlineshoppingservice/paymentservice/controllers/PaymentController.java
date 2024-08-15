package com.onlineshoppingservice.paymentservice.controllers;

import com.onlineshoppingservice.paymentservice.dtos.PaymentRequestDto;
import com.onlineshoppingservice.paymentservice.services.PaymentService;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/createPaymentLink")
    public String initiatePayment(@RequestBody PaymentRequestDto request) throws RazorpayException, StripeException {
        return paymentService.initiatePayment(request.getOrderId(), request.getEmail(), request.getPhnum());
    }
}
