package com.example.demo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemUpdateDto {
    private Integer quantity;
    private BigDecimal priceAtMoment;
    private String status;
}
