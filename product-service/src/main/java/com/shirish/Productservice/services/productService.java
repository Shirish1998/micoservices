package com.shirish.Productservice.services;

import com.shirish.Productservice.dto.ProductRequest;
import com.shirish.Productservice.dto.ProductResponse;
import com.shirish.Productservice.model.Product;
import com.shirish.Productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class productService {


    private final ProductRepository productRepository;

    @Autowired
    public productService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        Product savedProduct = this.productRepository.save(product);

        log.info("Product is saved with Id {}",savedProduct.getId());
    }

    public List<ProductResponse> getAllProducts(){

        log.info("Get All Products reqyest started");
        List<Product> allProduct = this.productRepository.findAll();

        List<ProductResponse> productResponses = allProduct.stream().map(product -> mapToProductResponse(product)).collect(Collectors.toList());

        log.info("Received total {} products form DB ", productResponses.size());
        return productResponses;
    }

    private ProductResponse mapToProductResponse(Product product) {

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
