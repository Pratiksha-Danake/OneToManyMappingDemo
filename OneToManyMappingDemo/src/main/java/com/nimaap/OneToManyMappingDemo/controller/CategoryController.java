package com.nimaap.OneToManyMappingDemo.controller;

import com.nimaap.OneToManyMappingDemo.dto.CreateCategoryRequestDto;
import com.nimaap.OneToManyMappingDemo.entity.Category;
import com.nimaap.OneToManyMappingDemo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category")
    ResponseEntity<Category> addCategory(@RequestBody CreateCategoryRequestDto requestDto) {
        Category createdCategory = categoryService.addCategory(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(createdCategory);
    }

    @GetMapping("category/{id}")
    ResponseEntity<Category> getCategory(@PathVariable Long id){
        Optional<Category> category = categoryService.getCategory(id);
        if (category.isPresent())
            return ResponseEntity.status(HttpStatus.FOUND).body(category.get());
        else
            return ResponseEntity.noContent().build();
    }
}
