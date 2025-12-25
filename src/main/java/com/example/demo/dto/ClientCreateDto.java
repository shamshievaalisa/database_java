package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ClientCreateDto {

    @NotBlank(message = "ФИО обязательно для заполнения")
    @Pattern(
            regexp = "^[а-яА-ЯёЁ\\s\\-]+$",
            message = "ФИО может содержать только кириллические буквы, пробелы и дефис"
    )
    private String fullName;

    @NotBlank(message = "Номер телефона обязателен")
    @Pattern(
            regexp = "^\\+?7\\d{10}$|^8\\d{10}$|^9\\d{9}$",
            message = "Номер телефона должен быть в формате: +79991234567, 89991234567 или 9991234567"
    )
    private String phone;
}