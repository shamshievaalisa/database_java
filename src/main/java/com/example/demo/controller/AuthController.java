package com.example.demo.controller;

import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.RegisterRequestDto;
import com.example.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid; // ⚠️ Импорт именно jakarta.validation.Valid

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequestDto dto) { // ✅ @Valid добавлен
        authService.register(
                dto.getLogin(),
                dto.getPassword(),
                dto.getFullName(),
                dto.getPosition(),
                dto.getRole() == null ? "USER" : dto.getRole()
        );
        return "registered";
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequestDto dto) { // ✅ @Valid добавлен
        return authService.login(dto);
    }
}