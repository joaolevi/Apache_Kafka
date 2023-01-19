package com.joaolevi.paymentservice.resource.impl;

import com.joaolevi.paymentservice.model.Payment;

    import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaolevi.paymentservice.resource.PaymentResource;
    import com.joaolevi.paymentservice.service.PaymentService;

    import lombok.RequiredArgsConstructor;

    @RequiredArgsConstructor
@RestController
@RequestMapping(value = "/payments") // endpoint
public class PaymentResourceImpl implements PaymentResource{
    
    private final PaymentService paymentService; //RequiredArgsConstructor
    
    @Override
    public ResponseEntity<Payment> payment(Payment payment) {
        paymentService.sendPayment(payment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
