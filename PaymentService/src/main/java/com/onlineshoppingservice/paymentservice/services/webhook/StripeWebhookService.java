package com.onlineshoppingservice.paymentservice.services.webhook;

import com.stripe.Stripe;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.StripeObject;
import com.stripe.net.Webhook;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StripeWebhookService {

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @Value("${stripe.webhook.secret}")
    private String stripeWebhookSecret;

    public void handleStripeWebhook(HttpServletRequest request) throws IOException {
        Stripe.apiKey =stripeSecretKey;

        // This is your Stripe CLI webhook secret for testing your endpoint locally.
        String endpointSecret = stripeWebhookSecret;


        String payload = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
        String sigHeader = request.getHeader("Stripe-Signature");
        Event event = null;

        try {
            event = Webhook.constructEvent(payload,sigHeader, endpointSecret);
        } catch (Exception e) {
            // Invalid payload
            System.out.println("⚠️  Webhook error while parsing basic request.");
        }

        // Deserialize the nested object inside the event
        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
        StripeObject stripeObject = null;
        if (dataObjectDeserializer.getObject().isPresent()) {
            stripeObject = dataObjectDeserializer.getObject().get();
        } else {
            // Deserialization failed, probably due to an API version mismatch.
            System.out.println("⚠️  Webhook error while parsing nested object.");
        }
        switch (event.getType()) {
            case "payment_link.created": {
                System.out.println("Payment link created");
                break;
            }
            case "payment_link.updated": {
                System.out.println("Payment link updated");
                break;
            }
            default:
                System.out.println("Unhandled event type: " + event.getType());
        }
    }
}
