package com.example.demo.controller;

import com.example.demo.dto.UserCreateDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserUpdateDto;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.security.JwtUtil; // ← импорт есть — хорошо
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final JwtUtil jwtUtil;

    @GetMapping
    public List<UserDto> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public UserDto one(@PathVariable Long id) {
        return service.one(id);
    }

    // ✅ ИСПРАВЛЕННЫЙ /me
    @GetMapping("/me")
    public UserDto me(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new EntityNotFoundException("Пользователь не авторизован");
        }
        String token = authHeader.substring(7);
        String login = jwtUtil.extractLogin(token); // ← используем внедрённый бин
        return service.findByUsername(login);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable Long id,
                          @RequestBody UserUpdateDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "deleted";
    }
}