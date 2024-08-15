package com.onlineshoppingservice.paymentservice.services.paymentgateway;

import com.onlineshoppingservice.paymentservice.dtos.OrderDetailsDto;
import com.onlineshoppingservice.paymentservice.dtos.PaymentGatewayResponseDto;
import com.onlineshoppingservice.paymentservice.model.PaymentStatus;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripePaymentGateway implements PaymentGateway {

    @Value("${stripe.secret.key}")
    private String STRIPE_SECRET_KEY;

    @Override
    public PaymentGatewayResponseDto generatePaymentLink(OrderDetailsDto orderDetailsDto, String email, String phNum) throws StripeException{
        Stripe.apiKey = STRIPE_SECRET_KEY;

        PriceCreateParams params =
                PriceCreateParams.builder()
                        .setCurrency("inr")
                        .setUnitAmount(orderDetailsDto.getAmount()*100)
                        .setProductData(
                                PriceCreateParams.ProductData.builder()
                                        .setName(orderDetailsDto.getProductName())
                                        .build()
                        )
                        .build();
        Price price = Price.create(params);

        PaymentLinkCreateParams paymentLinkCreateParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .build();
        PaymentLink paymentLink = PaymentLink.create(paymentLinkCreateParams);

        return PaymentGatewayResponseDto.builder()
                .paymentLink(paymentLink.getUrl())
                .paymentId(paymentLink.getId())
                .paymentStatus(String.valueOf(PaymentStatus.PENDING))
                .build();

    }
}
