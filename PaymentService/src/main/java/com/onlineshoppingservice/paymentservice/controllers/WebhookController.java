package com.onlineshoppingservice.paymentservice.controllers;

import com.onlineshoppingservice.paymentservice.services.webhook.RazorPayWebhookService;
import com.onlineshoppingservice.paymentservice.services.webhook.StripeWebhookService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private StripeWebhookService stripeWebhookService;

    private RazorPayWebhookService razorPayWebhookService;

    public WebhookController(StripeWebhookService stripeWebhookService, RazorPayWebhookService razorPayWebhookService) {
        this.stripeWebhookService = stripeWebhookService;
        this.razorPayWebhookService = razorPayWebhookService;
    }


    @PostMapping("/razorpayWebhook")
    public void razorpayWebhook(HttpServletRequest request) {
        System.out.println("Razorpay Webhook received");
        razorPayWebhookService.handleRazorPayWebhook(request);
    }

    @PostMapping("/stripeWebhook")
    public void stripeWebhook(HttpServletRequest request) throws IOException {
        System.out.println("Stripe Webhook received");
        stripeWebhookService.handleStripeWebhook(request);
    }
}
