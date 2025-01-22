package com.nimaap.OneToManyMappingDemo.controller;

import com.nimaap.OneToManyMappingDemo.dto.CreateCategoryRequestDto;
import com.nimaap.OneToManyMappingDemo.entity.Category;
import com.nimaap.OneToManyMappingDemo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("categories")
    ResponseEntity<List<Category>> getAllCategories(){
        List<Category> allCategories= categoryService.findAllCategories();
        if (allCategories.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.status(HttpStatus.OK).body(allCategories);
    }

    @DeleteMapping("category/{id}")
    ResponseEntity<String> deleteCategory(@PathVariable Long id){
        boolean isDeleted = categoryService.removeCategory(id);
        if (isDeleted)
            return ResponseEntity.status(HttpStatus.OK).body("Category Deleted Successfully..!");
        else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Category not found");
    }

}
