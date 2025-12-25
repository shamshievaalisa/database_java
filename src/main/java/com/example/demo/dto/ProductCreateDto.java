package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductCreateDto {

    @NotBlank(message = "Название товара обязательно")
    @Pattern(
            regexp = "^[\\p{L}\\p{N}\\s\\-_.()]+$",
            message = "Название товара может содержать только буквы, цифры, пробелы и символы: - _ . ( )"
    )
    private String name;

    @NotNull(message = "Цена обязательна")
    @DecimalMin(value = "0.01", message = "Цена должна быть положительной")
    @Digits(integer = 10, fraction = 2, message = "Цена может содержать до 10 цифр до запятой и 2 после")
    private BigDecimal price;

    @NotNull(message = "Категория обязательна")
    @Positive(message = "ID категории должен быть положительным числом")
    private Long categoryId;
}