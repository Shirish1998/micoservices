package com.shirish.inventoryservice.inventoryservice.service;

import com.shirish.inventoryservice.inventoryservice.dto.InventortyResponse;
import com.shirish.inventoryservice.inventoryservice.model.Inventory;
import com.shirish.inventoryservice.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    private  final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional(readOnly = true)
    public List<InventortyResponse> isInStock(List<String> skuCode){
       return this.inventoryRepository.findBySkuCodeIn(skuCode).stream()
                 .map(inventory ->
                     InventortyResponse.builder()
                             .skuCode(inventory.getSkuCode())
                             .isInStock(inventory.getQuantity() > 0)
                             .build()
                 ).toList();

    }
}
