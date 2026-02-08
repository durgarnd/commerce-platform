package com.dds.notification.service;

import com.dds.order.model.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @KafkaListener(topics = "order-confirmed")
    public void handleSuccess(OrderEvent event) {
        System.out.println("EMAIL SENT: Order confirmed → " + event.getOrderId());
    }

    @KafkaListener(topics = "order-failed")
    public void handleFailure(OrderEvent event) {
        System.out.println("EMAIL SENT: Order failed → " + event.getOrderId());
    }
}
