package com.onlineshoppingservice.paymentservice.services.webhook;

import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

@Service
public class RazorPayWebhookService {
        @Value("${razorpay.webhook.secret}")
        private String webhookSecret;

        public void handleRazorPayWebhook(HttpServletRequest request) {
            System.out.println("Razorpay Webhook received");
            String payload = "";
            String signature = "";

            // Read the payload
            try (BufferedReader reader = request.getReader()) {
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                payload = stringBuilder.toString();
            } catch (IOException e) {
                System.out.println("Error reading request payload");
            }

            // Get the signature from the header
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                if ("X-Razorpay-Signature".equals(headerName)) {
                    signature = request.getHeader(headerName);
                }
            }

            // Verify the signature
            try {
                boolean isValid = Utils.verifyWebhookSignature(payload, signature, webhookSecret);
                if (!isValid) {
                    System.out.println("Signature verification failed");
                }
            } catch (RazorpayException e) {
                System.out.println("Error verifying signature");
            }

            // Parse the event JSON
            JSONObject eventJson = new JSONObject(payload);
            String eventType = eventJson.getString("event");
            JSONObject eventData = eventJson.getJSONObject("payload");

            // Handle different event types
            switch (eventType) {
                case "payment.captured":
                    handlePaymentCaptured(eventData);
                    break;
                case "payment.failed":
                    handlePaymentFailed(eventData);
                    break;
                // Add more cases for other event types you want to handle
                default:
                    System.out.println("Unhandled event type: " + eventType);
            }
        }
    public static void handlePaymentCaptured(JSONObject eventData) {
        JSONObject paymentEntity = eventData.getJSONObject("payment").getJSONObject("entity");
        String paymentId = paymentEntity.getString("id");
        String amount = paymentEntity.getString("amount");
        String status = paymentEntity.getString("status");

        // Process the payment captured event
        System.out.println("Payment captured: " + paymentId);
        System.out.println("Amount: " + amount);
        System.out.println("Status: " + status);
    }

    public static void handlePaymentFailed(JSONObject eventData) {
        JSONObject paymentEntity = eventData.getJSONObject("payment").getJSONObject("entity");
        String paymentId = paymentEntity.getString("id");
        String amount = paymentEntity.getString("amount");
        String status = paymentEntity.getString("status");

        // Process the payment failed event
        System.out.println("Payment failed: " + paymentId);
        System.out.println("Amount: " + amount);
        System.out.println("Status: " + status);
    }
}
