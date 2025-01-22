package com.nimaap.OneToManyMappingDemo.dto;

import lombok.Data;

@Data
public class CreateProductRequestDto {
    public String name;
    public String description;
    public double price;
    public Long category_id;
}
