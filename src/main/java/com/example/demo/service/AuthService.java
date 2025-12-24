package com.example.demo.service;

import com.example.demo.dto.LoginRequestDto;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwt;

    public void register(String login,
                         String password,
                         String fullName,
                         String position,
                         String role) {

        if (repo.findByLogin(login).isPresent()) {
            throw new EntityNotFoundException("Пользователь с таким логином уже существует");
        }

        User user = User.builder()
                .login(login)
                .password(encoder.encode(password))
                .fullName(fullName)
                .position(position)
                .role(role)
                .build();

        repo.save(user);
    }

    public String login(LoginRequestDto dto) {
        User user = repo.findByLogin(dto.getLogin())
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        if (!encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new EntityNotFoundException("Неверный пароль");
        }

        return jwt.generateToken(user.getLogin(), user.getRole());
    }
}