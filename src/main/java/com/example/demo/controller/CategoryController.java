package com.example.demo.controller;

import com.example.demo.dto.CategoryCreateDto;
import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.CategoryUpdateDto;
import com.example.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid; // ⚠️ Импорт для @Valid
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping
    public List<CategoryDto> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public CategoryDto one(@PathVariable Long id) {
        return service.one(id);
    }

    @PostMapping
    public CategoryDto create(@Valid @RequestBody CategoryCreateDto dto) { // ✅
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public CategoryDto update(@PathVariable Long id,
                              @Valid @RequestBody CategoryUpdateDto dto) { // ✅
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "deleted";
    }
}