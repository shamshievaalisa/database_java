package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CategoryCreateDto {

    @NotBlank(message = "Название категории не может быть пустым")
    @Pattern(
            regexp = "^[\\p{L}\\p{N}\\s\\-_.()]+$",
            message = "Название категории может содержать только буквы, цифры, пробелы и символы: - _ . ( )"
    )
    private String name;
}