package com.shirish.orderservice.orderservicr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineItemaDto {


    private String skuCode;

    private BigDecimal price;

    private Integer quantity;
}
