package com.shirish.inventoryservice.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventortyResponse {

    private String skuCode;
    private boolean isInStock;
}
