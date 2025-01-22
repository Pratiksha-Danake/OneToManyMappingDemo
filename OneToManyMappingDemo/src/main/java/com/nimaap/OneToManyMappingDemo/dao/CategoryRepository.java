package com.nimaap.OneToManyMappingDemo.dao;

import com.nimaap.OneToManyMappingDemo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
