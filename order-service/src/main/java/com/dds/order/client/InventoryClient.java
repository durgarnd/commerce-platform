package com.dds.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service")
public interface InventoryClient {

    @GetMapping("/inventory/check")
    boolean checkStock(
            @RequestParam String productCode,
            @RequestParam int quantity
    );
}
