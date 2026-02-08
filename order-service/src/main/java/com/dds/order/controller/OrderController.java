package com.dds.order.controller;

import com.dds.order.dto.OrderRequest;
import com.dds.order.model.OrderStatus;
import com.dds.order.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public OrderStatus create(@RequestBody OrderRequest request) {
        return service.placeOrder(request);
    }
}
