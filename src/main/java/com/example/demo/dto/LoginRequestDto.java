package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "Логин не может быть пустым")
    @Size(max = 100, message = "Логин не может быть длиннее 100 символов")
    @Pattern(
            regexp = "^[a-zA-Z0-9._-]+$",
            message = "Логин может содержать только латинские буквы, цифры, точки, дефисы и подчёркивания"
    )
    private String login;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 6, max = 128, message = "Пароль должен содержать от 6 до 128 символов")
    private String password;
}