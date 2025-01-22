package com.nimaap.OneToManyMappingDemo.controller;

import com.nimaap.OneToManyMappingDemo.dto.CreateCategoryRequestDto;
import com.nimaap.OneToManyMappingDemo.dto.CreateProductRequestDto;
import com.nimaap.OneToManyMappingDemo.entity.Category;
import com.nimaap.OneToManyMappingDemo.entity.Product;
import com.nimaap.OneToManyMappingDemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("products")
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Product> productPage = productService.findAllProducts(page, size);
        return ResponseEntity.ok(productPage);
    }

    @DeleteMapping("product/{id}")
    ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        boolean isDeleted = productService.removeProduct(id);
        if (isDeleted)
            return ResponseEntity.status(HttpStatus.OK).body("Product Removed Successfully..!");
        else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product not found");
    }

    @PutMapping("product/{id}")
    ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody CreateProductRequestDto requestDto) {
        Optional<Product> updatedProduct = productService.updateProductDetails(id, requestDto);
        if (updatedProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedProduct.get());
        } else
            return ResponseEntity.noContent().build();
    }
}
