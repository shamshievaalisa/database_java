package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CategoryDto {
    private Long id;

    @NotBlank(message = "Имя категории не может быть пустым")
    @Pattern(
            regexp = "^[\\p{L}\\p{N}\\s\\-_.()]+$",
            message = "Имя категории содержит недопустимые символы"
    )
    private String name;
}