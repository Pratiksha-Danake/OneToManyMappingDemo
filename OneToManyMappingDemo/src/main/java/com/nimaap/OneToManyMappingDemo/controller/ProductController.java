package com.nimaap.OneToManyMappingDemo.controller;

import com.nimaap.OneToManyMappingDemo.dto.CreateProductRequestDto;
import com.nimaap.OneToManyMappingDemo.entity.Category;
import com.nimaap.OneToManyMappingDemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController("/api/v1")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("product")
    ResponseEntity<ProductResponseDto> addProduct(@RequestBody CreateProductRequestDto productRequestDto) {
        ProductResponseDto addedProduct = productService.addProduct(productRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(addedProduct);
    }

    @GetMapping("product/{id}")
    ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id) {
        ProductResponseDto product = productService.getProductBy(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }
}
