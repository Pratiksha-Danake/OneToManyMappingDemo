package com.nimaap.OneToManyMappingDemo.controller;

import com.nimaap.OneToManyMappingDemo.dto.CreateProductRequestDto;
import com.nimaap.OneToManyMappingDemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("product")
    ResponseEntity<ProductResponseDto> addProduct(@RequestBody CreateProductRequestDto productRequestDto) {
        ProductResponseDto addedProduct = productService.addProduct(productRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(addedProduct);
    }
}
