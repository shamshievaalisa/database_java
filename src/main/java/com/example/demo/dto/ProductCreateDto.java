package com.example.demo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductCreateDto {
    private String name;
    private BigDecimal price;
    private Long categoryId;
}
