package com.shirish.Productservice.Controller;

import com.shirish.Productservice.dto.ProductRequest;
import com.shirish.Productservice.dto.ProductResponse;
import com.shirish.Productservice.services.productService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@Slf4j
public class ProductController {

    private final productService productService;

    @Autowired
    public ProductController(com.shirish.Productservice.services.productService productService) {
        this.productService = productService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody ProductRequest product){
        log.info("Inside "+this.getClass().getName()+" Controller to save the new product");
        this.productService.createProduct(product);
        log.info("save product method completed");
    }

    @GetMapping
    public List<ProductResponse> getAllProduct(){
     return this.productService.getAllProducts();
    }


}
