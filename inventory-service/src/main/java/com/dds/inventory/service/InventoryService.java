package com.dds.inventory.service;


import com.dds.inventory.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    public boolean isInStock(String productCode, int quantity) {
        return repository.findByProductCode(productCode)
                .map(p -> p.getAvailableQuantity() >= quantity)
                .orElse(false);
    }
}
