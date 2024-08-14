package com.onlineshoppingservice.paymentservice.controllers;

import com.onlineshoppingservice.paymentservice.dtos.PaymentDto;
import com.onlineshoppingservice.paymentservice.services.PaymentService;
import com.razorpay.RazorpayException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/createPaymentLink")
    public String initiatePayment(@RequestBody PaymentDto request) throws RazorpayException {
        return paymentService.initiatePayment(request.getOrderId(), request.getEmail(), request.getPhnum());
    }
}
