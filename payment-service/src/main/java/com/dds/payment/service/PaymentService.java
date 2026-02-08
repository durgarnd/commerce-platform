package com.dds.payment.service;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentService {

    private final Random random = new Random();

    @CircuitBreaker(name = "paymentCB", fallbackMethod = "fallback")
    @Retry(name = "paymentRetry")
    public String processPayment(String orderId, double amount) {

        int chance = random.nextInt(10);

        if (chance < 5) {
            throw new RuntimeException("Payment gateway timeout");
        }

        return "PAYMENT_SUCCESS";
    }

    public String fallback(String orderId, double amount, Throwable ex) {
        return "PAYMENT_FAILED";
    }
}
