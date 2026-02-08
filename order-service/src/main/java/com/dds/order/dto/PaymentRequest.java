package com.dds.order.dto;

public class PaymentRequest {
    private String orderId;
    private double amount;

    public PaymentRequest(String s, double amount) {
    }
    public PaymentRequest() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
