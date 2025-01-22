package com.nimaap.OneToManyMappingDemo.dto;

import lombok.Data;

@Data
public class ProductRequestDto {
    public String name;
    public String description;
    public double price;
    public Long category_id;
}
