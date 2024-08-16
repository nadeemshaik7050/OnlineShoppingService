package com.onlineshoppingservice.paymentservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlineshoppingservice.paymentservice.dtos.*;
import com.onlineshoppingservice.paymentservice.model.PaymentDetails;
import com.onlineshoppingservice.paymentservice.repository.PaymentRepository;
import com.onlineshoppingservice.paymentservice.services.paymentgateway.PaymentGateway;
import com.onlineshoppingservice.paymentservice.stratergies.PaymentGatewayChooseStratergy;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentGatewayChooseStratergy paymentGatewayChooseStratergy;

    private OrderService orderService;

    private PaymentRepository paymentRepository;

    private KafkaTemplate<String, String> kafkaTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public PaymentService(PaymentGatewayChooseStratergy paymentGatewayChooseStratergy, OrderService orderService, PaymentRepository paymentRepository, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.paymentGatewayChooseStratergy = paymentGatewayChooseStratergy;
        this.orderService = orderService;
        this.paymentRepository = paymentRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public String initiatePayment(String orderId, String email, String phnum) throws RazorpayException, StripeException, JsonProcessingException {
        OrderDetailsDto orderDetailsDto = orderService.getOrderDetails(orderId);
        PaymentGateway paymentGateway = paymentGatewayChooseStratergy.choosePaymentGateway();
        String paymentGatewayName = paymentGateway.getClass().getSimpleName();
        PaymentGatewayResponseDto paymentGatewayResponseDto = paymentGateway.generatePaymentLink(orderDetailsDto, email, phnum);

        PaymentDetails paymentDetails = PaymentDetails.builder()
                .orderId(orderDetailsDto.getId())
                .amount(orderDetailsDto.getAmount())
                .productName(orderDetailsDto.getProductName())
                .quantity(orderDetailsDto.getQuantity())
                .discountInPercentage(orderDetailsDto.getDiscountInPercentage())
                .paymentUrl(paymentGatewayResponseDto.getPaymentLink())
                .email(email)
                .phNum(phnum)
                .paymentStatus(paymentGatewayResponseDto.getPaymentStatus())
                .paymentGateway(paymentGatewayName)
                .paymentIdInGateway(paymentGatewayResponseDto.getPaymentId())
                .build();

        paymentRepository.save(paymentDetails);

        EmailDto emailDto = EmailDto.builder()
                .recipientEmail(email)
                .subject("Payment Link for Order: " + orderDetailsDto.getId())
                .message("Please click on the below link to make the payment: " + paymentGatewayResponseDto.getPaymentLink())
                .build();

        SMSDto smsDto = SMSDto.builder()
                .recipientPhNum(phnum)
                .message("Please click on the below link to make the payment: " + paymentGatewayResponseDto.getPaymentLink())
                .build();


        kafkaTemplate.send(Constants.Email, objectMapper.writeValueAsString(emailDto));

        //kafkaTemplate.send(Constants.SMS, objectMapper.writeValueAsString(smsDto));

        return  paymentGatewayResponseDto.getPaymentLink();
    }
}
