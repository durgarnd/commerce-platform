package com.dds.inventory.controller;
import com.dds.inventory.service.InventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @GetMapping("/check")
    public boolean checkStock(
            @RequestParam String productCode,
            @RequestParam int quantity) {

        return service.isInStock(productCode, quantity);
    }
}
