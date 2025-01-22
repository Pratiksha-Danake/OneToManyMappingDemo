package com.nimaap.OneToManyMappingDemo.service;

import com.nimaap.OneToManyMappingDemo.dao.CategoryRepository;
import com.nimaap.OneToManyMappingDemo.dto.CreateCategoryRequestDto;
import com.nimaap.OneToManyMappingDemo.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category addCategory(CreateCategoryRequestDto requestDto) {
        Category category = Category.builder()
                .name(requestDto.name)
                .build();
        return categoryRepository.save(category);
    }

    public Optional<Category> getCategory(Long id) {
        if (categoryRepository.existsById(id))
            return categoryRepository.findById(id);
        return Optional.empty();
    }

    public boolean removeCategory(Long id) {
        if (categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
            return true;
        }
        else
            return false;
    }
}
