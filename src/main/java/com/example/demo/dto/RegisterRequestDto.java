package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterRequestDto {

    @NotBlank(message = "Логин обязателен")
    @Size(max = 50, message = "Логин не может быть длиннее 50 символов")
    @Pattern(
            regexp = "^[a-zA-Z0-9._-]+$",
            message = "Логин может содержать только латинские буквы, цифры, точки, дефисы и подчёркивания"
    )
    private String login;

    @NotBlank(message = "Пароль обязателен")
    @Size(min = 6, max = 128, message = "Пароль должен содержать от 6 до 128 символов")
    private String password;

    @NotBlank(message = "ФИО обязательно")
    @Size(max = 100, message = "ФИО не может быть длиннее 100 символов")
    @Pattern(
            regexp = "^[а-яА-ЯёЁ\\s\\-]+$",
            message = "ФИО может содержать только кириллические буквы, пробелы и дефис"
    )
    private String fullName;

    @NotBlank(message = "Должность обязательна")
    @Size(max = 100, message = "Должность не может быть длиннее 100 символов")
    @Pattern(
            regexp = "^[а-яА-ЯёЁ\\s\\-]+$",
            message = "Должность может содержать только кириллические буквы, пробелы и дефис"
    )
    private String position;

    @NotBlank(message = "Роль обязательна")
    @Pattern(
            regexp = "^(USER|MANAGER)$",
            message = "Недопустимая роль. Допустимые значения: USER, MANAGER"
    )
    private String role;
}