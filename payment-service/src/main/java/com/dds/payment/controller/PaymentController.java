package com.dds.payment.controller;



import com.dds.payment.dto.PaymentRequest;
import com.dds.payment.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping
    public String pay(@RequestBody PaymentRequest request) {
        return service.processPayment(
                request.getOrderId(),
                request.getAmount()
        );
    }
}
