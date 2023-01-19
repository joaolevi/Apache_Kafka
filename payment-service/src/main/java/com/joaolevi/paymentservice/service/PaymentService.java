package com.joaolevi.paymentservice.service;

import com.joaolevi.paymentservice.model.Payment;

public interface PaymentService {
    
    void sendPayment(Payment payment);
}
