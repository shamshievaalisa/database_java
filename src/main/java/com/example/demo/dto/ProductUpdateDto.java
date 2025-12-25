package com.example.demo.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpdateDto {

    @Pattern(
            regexp = "^[\\p{L}\\p{N}\\s\\-_.()]+$",
            message = "Название товара может содержать только буквы, цифры, пробелы и символы: - _ . ( )"
    )
    private String name;

    @DecimalMin(value = "0.01", message = "Цена должна быть положительной")
    @Digits(integer = 10, fraction = 2, message = "Цена может содержать до 10 цифр до запятой и 2 после")
    private BigDecimal price;

    @Positive(message = "ID категории должен быть положительным числом")
    private Long categoryId;
}