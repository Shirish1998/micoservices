package com.shirish.inventoryservice.inventoryservice.controller;

import com.shirish.inventoryservice.inventoryservice.dto.InventortyResponse;
import com.shirish.inventoryservice.inventoryservice.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventortyResponse> isInStock(@RequestParam List<String> skuCode){

        log.info("Inside isIn stock method "+ this.getClass().getName());
        return this.inventoryService.isInStock(skuCode);
    }
}
