package com.onlineshoppingservice.paymentservice.services.paymentgateway;

import com.onlineshoppingservice.paymentservice.dtos.OrderDetailsDto;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class RazorpayPaymentGateway implements PaymentGateway {

    private RazorpayClient razorpayClient;

    RazorpayPaymentGateway(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    @Override
    public String generatePaymentLink(OrderDetailsDto orderDetailsDto,String email,String phNum) throws RazorpayException {

        //Creating Payment Link
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount", orderDetailsDto.getAmount()*100);
        paymentLinkRequest.put("currency", "INR");
        paymentLinkRequest.put("expire_by", Instant.now().getEpochSecond() + 3600); // 1 hour
        paymentLinkRequest.put("reference_id", "Order_" + orderDetailsDto.getId());
        paymentLinkRequest.put("description", "Payment for order " +  orderDetailsDto.getId());

        //Adding Customer Details
        JSONObject customer = new JSONObject();
        customer.put("name", "Nadeem shaik");
        customer.put("contact", phNum);
        customer.put("email", email);

        paymentLinkRequest.put("customer", customer);

        JSONObject notify = new JSONObject();
        notify.put("sms", true);
        notify.put("email", true);
        paymentLinkRequest.put("notify", notify);
        paymentLinkRequest.put("reminder_enable", true);
        paymentLinkRequest.put("callback_url", "https://example-callback-url.com/");
        paymentLinkRequest.put("callback_method", "get");

        PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
        return payment.get("short_url").toString();
    }


}
