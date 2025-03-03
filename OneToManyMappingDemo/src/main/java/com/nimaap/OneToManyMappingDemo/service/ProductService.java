package com.nimaap.OneToManyMappingDemo.service;

import com.nimaap.OneToManyMappingDemo.controller.ProductResponseDto;
import com.nimaap.OneToManyMappingDemo.dao.CategoryRepository;
import com.nimaap.OneToManyMappingDemo.dao.ProductRepository;
import com.nimaap.OneToManyMappingDemo.dto.ProductRequestDto;
import com.nimaap.OneToManyMappingDemo.entity.Category;
import com.nimaap.OneToManyMappingDemo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
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

    public Optional<Product> updateProductDetails(Long id, ProductRequestDto requestDto) {
        Optional<Category> categoryOptional = categoryRepository.findById(requestDto.getCategory_id());

        return productRepository.findById(id).map(product -> {
            product.setName(requestDto.getName());
            product.setDescription(requestDto.getDescription());
            product.setPrice(requestDto.getPrice());
            product.setCategory(categoryOptional.get());
            return productRepository.save(product);
        });
    }

    public Page<Product> findAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }
}