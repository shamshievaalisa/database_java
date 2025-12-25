package com.example.demo.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {

    @Pattern(
            regexp = "^[а-яА-ЯёЁ\\s\\-]+$",
            message = "ФИО может содержать только кириллицу, пробелы и дефис"
    )
    @Size(max = 100, message = "ФИО не может быть длиннее 100 символов")
    private String fullName;

    @Pattern(
            regexp = "^[а-яА-ЯёЁ\\s\\-]+$",
            message = "Должность может содержать только кириллицу, пробелы и дефис"
    )
    @Size(max = 100, message = "Должность не может быть длиннее 100 символов")
    private String position;

    // ⚠️ ОСТОРОЖНО: обновление роли!
    @Pattern(
            regexp = "^(USER|MANAGER|TECHNOLOGIST)$",
            message = "Недопустимая роль. Допустимые значения: USER, MANAGER, TECHNOLOGIST"
    )
    private String role;
}