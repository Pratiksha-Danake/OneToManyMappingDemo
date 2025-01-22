package com.nimaap.OneToManyMappingDemo.service;

import com.nimaap.OneToManyMappingDemo.controller.ProductController;
import com.nimaap.OneToManyMappingDemo.controller.ProductResponseDto;
import com.nimaap.OneToManyMappingDemo.dao.CategoryRepository;
import com.nimaap.OneToManyMappingDemo.dao.ProductRepository;
import com.nimaap.OneToManyMappingDemo.dto.CreateProductRequestDto;
import com.nimaap.OneToManyMappingDemo.entity.Category;
import com.nimaap.OneToManyMappingDemo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;
    public ProductResponseDto addProduct(CreateProductRequestDto productRequestDto) {
        Optional<Category> category = categoryRepository.findById(productRequestDto.getCategory_id());
        Product productToAdd = Product.builder()
                .name(productRequestDto.getName())
                .description(productRequestDto.getDescription())
                .price(productRequestDto.getPrice())
                .category(category.get())
                .build();

        productRepository.save(productToAdd);
        return ProductResponseDto.builder()
                .id(productToAdd.getId())
                .name(productToAdd.getName())
                .description(productToAdd.getDescription())
                .price(productToAdd.getPrice())
                .category_id(category.get().getId())
                .category_name(category.get().getName())
                .build();
    }

    public ProductResponseDto getProductBy(Long id) {
        Optional<Product> product = productRepository.findById(id);

        return ProductResponseDto.builder()
                .id(product.get().getId())
                .name(product.get().getName())
                .description(product.get().getDescription())
                .price(product.get().getPrice())
                .category_id(product.get().getCategory().getId())
                .category_name(product.get().getCategory().getName())
                .build();
    }


    public boolean removeProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        } else
            return false;
    }
}
