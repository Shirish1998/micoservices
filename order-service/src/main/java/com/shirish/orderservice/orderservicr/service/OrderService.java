package com.shirish.orderservice.orderservicr.service;

import com.shirish.orderservice.orderservicr.dto.InventortyResponse;
import com.shirish.orderservice.orderservicr.dto.OrderLineItemaDto;
import com.shirish.orderservice.orderservicr.dto.OrderRequest;
import com.shirish.orderservice.orderservicr.model.Order;
import com.shirish.orderservice.orderservicr.model.OrderLineIteam;
import com.shirish.orderservice.orderservicr.repository.OrderRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class OrderService {

    private final OrderRepo orderRepo;
    private final WebClient.Builder webClientBuilder;

    @Autowired
    public OrderService(OrderRepo orderRepo, WebClient.Builder webClientBuilder) {
        this.orderRepo = orderRepo;
        this.webClientBuilder = webClientBuilder;
    }

    public void placeorder(OrderRequest orderRequest){
        log.info("Inside place order method of service class");
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        log.info("Converting OrderLineItemaDtosList to OrderLineItemaDtos");
        List<OrderLineIteam> orderLineIteams = orderRequest.getOrderLineItemaDtosList()
                .stream()
                .map(orderLineItemaDto -> mapTODto(orderLineItemaDto))
                .collect(Collectors.toList());

        order.setOrderLineIteams(orderLineIteams);

        // call inventorty service only if the product is available
        // use web client for microservice call
        List<String> skuCodes = order.getOrderLineIteams().stream()
                .map(orderLineIteam -> orderLineIteam.getSkuCode())
                .collect(Collectors.toList());
        boolean allProductInStock = false;

        try {
            InventortyResponse[] inventoryResponse = webClientBuilder.build().get()
                    .uri("http://inventory-service/api/inventory",
                            uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                    .retrieve()
                    .bodyToMono(InventortyResponse[].class)
                    .block();
             allProductInStock = Arrays.stream(inventoryResponse).allMatch(inventortyResponse1 -> inventortyResponse1.isInStock());
        } catch (WebClientResponseException e) {
            // Handle HTTP response exceptions
            System.err.println("HTTP error: " + e.getStatusCode());
        } catch (Exception e) {
            // Handle other exceptions (like network errors)
            System.err.println("Error: " + e.getMessage());
        }


        if(allProductInStock){
            this.orderRepo.save(order);
        } else {
            throw new IllegalArgumentException("Product is out of stock");
        }
        log.info("place order method of service class completed");
    }

    private OrderLineIteam mapTODto(OrderLineItemaDto orderLineItemaDto) {

        return OrderLineIteam
               .builder()
               .price(orderLineItemaDto.getPrice())
               .quantity(orderLineItemaDto.getQuantity())
               .skuCode(orderLineItemaDto.getSkuCode())
               .build();
    }
}
