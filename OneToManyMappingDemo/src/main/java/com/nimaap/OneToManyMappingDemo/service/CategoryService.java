package com.nimaap.OneToManyMappingDemo.service;

import com.nimaap.OneToManyMappingDemo.dao.CategoryRepository;
import com.nimaap.OneToManyMappingDemo.dto.CreateCategoryRequestDto;
import com.nimaap.OneToManyMappingDemo.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<Category> findAllCategories(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return categoryRepository.findAll(pageable);
    }


    public boolean removeCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        } else
            return false;
    }

    public Optional<Category> updateCategoryDetails(Long id, CreateCategoryRequestDto updatedDto) {
        return categoryRepository.findById(id).map(category -> {
            category.setName(updatedDto.getName());
            return categoryRepository.save(category);
        });
    }
}
