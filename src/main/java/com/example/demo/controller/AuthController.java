package com.example.demo.controller;

import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.RegisterRequestDto;
import com.example.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequestDto dto) {
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
    public String login(@RequestBody LoginRequestDto dto) {
        return authService.login(dto);
    }
}
