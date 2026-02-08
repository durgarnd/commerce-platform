package com.dds.order.service;

import com.dds.order.client.InventoryClient;
import com.dds.order.client.PaymentClient;
import com.dds.order.dto.OrderRequest;
import com.dds.order.dto.PaymentRequest;
import com.dds.order.model.OrderEvent;
import com.dds.order.model.OrderStatus;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final InventoryClient inventoryClient;
    private final PaymentClient paymentClient;

    private final OrderEventProducer producer;

    public OrderService(
            InventoryClient inventoryClient,
            PaymentClient paymentClient,OrderEventProducer producer) {
        this.inventoryClient = inventoryClient;
        this.paymentClient = paymentClient;
        this.producer = producer;
    }

    public OrderStatus placeOrder(OrderRequest request) {
        OrderStatus status=OrderStatus.OUT_OF_STOCK;
        boolean inStock = inventoryClient.checkStock(
                request.getProductCode(),
                request.getQuantity()
        );

        if (!inStock) {
            status= OrderStatus.OUT_OF_STOCK;
        }

        PaymentRequest payment =
                new PaymentRequest("ORD-" + System.currentTimeMillis(),
                        request.getAmount());

        String result = paymentClient.makePayment(payment);

        if ("PAYMENT_FAILED".equals(result)) {
            status= OrderStatus.PAYMENT_FAILED;
        }
        else{
            status=OrderStatus.CONFIRMED;;
        }

        if (status == OrderStatus.CONFIRMED) {
            producer.publish("order-confirmed",
                    new OrderEvent("11", "CONFIRMED",
                            request.getProductCode(),
                            request.getQuantity(),
                            request.getAmount()));
        } else {
            producer.publish("order-failed",
                    new OrderEvent("111", status.name(),
                            request.getProductCode(),
                            request.getQuantity(),
                            request.getAmount()));
        }

        return status;
    }
}
