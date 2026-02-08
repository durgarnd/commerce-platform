package com.dds.inventory.repository;

import com.dds.inventory.entity.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository
        extends JpaRepository<ProductInventory, Long> {

    Optional<ProductInventory> findByProductCode(String productCode);


}
