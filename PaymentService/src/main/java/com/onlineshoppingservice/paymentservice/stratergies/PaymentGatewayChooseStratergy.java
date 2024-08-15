package com.onlineshoppingservice.paymentservice.stratergies;

import com.onlineshoppingservice.paymentservice.services.paymentgateway.PaymentGateway;
import com.onlineshoppingservice.paymentservice.services.paymentgateway.RazorpayPaymentGateway;
import com.onlineshoppingservice.paymentservice.services.paymentgateway.StripePaymentGateway;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentGatewayChooseStratergy {

    private RazorpayPaymentGateway razorpayPaymentGateway;
    private StripePaymentGateway stripePaymentGateway;

    public PaymentGatewayChooseStratergy(RazorpayPaymentGateway razorpayPaymentGateway, StripePaymentGateway stripePaymentGateway) {
        this.razorpayPaymentGateway = razorpayPaymentGateway;
        this.stripePaymentGateway = stripePaymentGateway;
    }

    public PaymentGateway choosePaymentGateway() {

        int random = new Random().nextInt();
        if (random % 2 == 0) {
            return stripePaymentGateway;
        }
        return razorpayPaymentGateway;
    }


}

