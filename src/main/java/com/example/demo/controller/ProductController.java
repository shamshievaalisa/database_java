package com.example.demo.controller;

import com.example.demo.dto.ProductCreateDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductUpdateDto;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid; // ⚠️ Обязательный импорт
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping
    public List<ProductDto> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public ProductDto one(@PathVariable Long id) {
        return service.one(id);
    }

    @PostMapping
    public ProductDto create(@Valid @RequestBody ProductCreateDto dto) { // ✅ @Valid добавлен
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public ProductDto update(@PathVariable Long id,
                             @Valid @RequestBody ProductUpdateDto dto) { // ✅ @Valid добавлен
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "deleted";
    }
}