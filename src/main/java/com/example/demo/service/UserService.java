package com.example.demo.service;

import com.example.demo.dto.UserCreateDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserUpdateDto;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserDto findByUsername(String login) {
        User user = repo.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден: " + login));
        return toDto(user);
    }

    /**
     * Преобразует сущность User в DTO.
     */
    private UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getLogin(),
                user.getRole(),
                user.getFullName(),
                user.getPosition()
        );
    }

    /**
     * Возвращает список всех пользователей.
     */
    public List<UserDto> all() {
        return repo.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    /**
     * Возвращает пользователя по ID.
     */
    public UserDto one(Long id) {
        User user = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
        return toDto(user);
    }

    /**
     * Создаёт нового пользователя.
     */
    public UserDto create(UserCreateDto dto) {
        if (repo.findByLogin(dto.getLogin()).isPresent()) {
            throw new EntityNotFoundException("Логин уже занят");
        }

        User user = User.builder()
                .login(dto.getLogin())
                .password(encoder.encode(dto.getPassword()))
                .role(dto.getRole())
                .fullName(dto.getFullName())
                .position(dto.getPosition())
                .build();

        repo.save(user);
        return toDto(user);
    }

    /**
     * Обновляет существующего пользователя.
     */
    public UserDto update(Long id, UserUpdateDto dto) {
        User user = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        user.setFullName(dto.getFullName());
        user.setPosition(dto.getPosition());
        user.setRole(dto.getRole());

        repo.save(user);
        return toDto(user);
    }

    /**
     * Удаляет пользователя по ID.
     */
    public void delete(Long id) {
        repo.deleteById(id);
    }
}