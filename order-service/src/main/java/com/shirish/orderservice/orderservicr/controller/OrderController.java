package com.shirish.orderservice.orderservicr.controller;

import com.shirish.orderservice.orderservicr.dto.OrderRequest;
import com.shirish.orderservice.orderservicr.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory",fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        log.info("place order request started");
        this.orderService.placeorder(orderRequest);
        return "Order placed successfully";
    }

    public String fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException){
        return "Oops something went wrong";
    }
}
