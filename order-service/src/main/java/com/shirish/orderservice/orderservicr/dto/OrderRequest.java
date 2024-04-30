package com.shirish.orderservice.orderservicr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {

    List<OrderLineItemaDto> orderLineItemaDtosList;
}
