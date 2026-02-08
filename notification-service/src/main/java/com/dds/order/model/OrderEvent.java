package com.dds.order.model;

import java.io.Serializable;

public class OrderEvent implements Serializable {
    String orderId;
    String status;
    String productCode;
    int quantity;
    double amount;

    public OrderEvent(String orderId, String status, String productCode, int quantity, double amount) {
        this.orderId = orderId;
        this.status = status;
        this.productCode = productCode;
        this.quantity = quantity;
        this.amount = amount;
    }

    public OrderEvent() {

    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
