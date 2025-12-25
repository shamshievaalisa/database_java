package com.example.demo.controller;

import com.example.demo.dto.ClientCreateDto;
import com.example.demo.dto.ClientDto;
import com.example.demo.dto.ClientUpdateDto;
import com.example.demo.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid; // ⚠️ Импорт для @Valid
import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;

    @GetMapping
    public List<ClientDto> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public ClientDto one(@PathVariable Long id) {
        return service.one(id);
    }

    @GetMapping("/count")
    public long count() {
        return service.count();
    }

    @PostMapping
    public ClientDto create(@Valid @RequestBody ClientCreateDto dto) { // ✅ @Valid добавлен
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public ClientDto update(@PathVariable Long id, @Valid @RequestBody ClientUpdateDto dto) { // ✅ @Valid добавлен
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "deleted";
    }
}