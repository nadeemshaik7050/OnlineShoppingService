package com.onlineshoppingservice.paymentservice.services;

import com.onlineshoppingservice.paymentservice.dtos.OrderDetailsDto;
import com.onlineshoppingservice.paymentservice.model.PaymentDetails;
import com.onlineshoppingservice.paymentservice.repository.PaymentRepository;
import com.onlineshoppingservice.paymentservice.services.paymentgateway.PaymentGateway;
import com.onlineshoppingservice.paymentservice.stratergies.PaymentGatewayChooseStratergy;
import com.razorpay.RazorpayException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentGatewayChooseStratergy paymentGatewayChooseStratergy;

    private OrderService orderService;

    private PaymentRepository paymentRepository;

    public PaymentService(PaymentGatewayChooseStratergy paymentGatewayChooseStratergy, OrderService orderService, PaymentRepository paymentRepository) {
        this.paymentGatewayChooseStratergy = paymentGatewayChooseStratergy;
        this.orderService = orderService;
        this.paymentRepository = paymentRepository;
    }

    public String initiatePayment(String orderId, String email, String phnum) throws RazorpayException {
        OrderDetailsDto orderDetailsDto = orderService.getOrderDetails(orderId);
        PaymentGateway paymentGateway = paymentGatewayChooseStratergy.choosePaymentGateway();
        String paymentUrl= paymentGateway.generatePaymentLink(orderDetailsDto, email, phnum);

        PaymentDetails paymentDetails = PaymentDetails.builder()
                .orderId(orderDetailsDto.getId())
                .amount(orderDetailsDto.getAmount())
                .productName(orderDetailsDto.getProductName())
                .quantity(orderDetailsDto.getQuantity())
                .discountInPercentage(orderDetailsDto.getDiscountInPercentage())
                .paymentUrl(paymentUrl)
                .email(email)
                .phNum(phnum)
                .build();

        paymentRepository.save(paymentDetails);

        return  paymentUrl;
    }
}
