package com.nimaap.OneToManyMappingDemo.controller;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDto {
          public Long id;
          public String name;
          public String description;
          public double price;
          public Long category_id;
          public String category_name;
}
