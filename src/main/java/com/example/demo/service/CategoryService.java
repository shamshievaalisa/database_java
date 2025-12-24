package com.example.demo.service;

import com.example.demo.dto.CategoryCreateDto;
import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.CategoryUpdateDto;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repo;

    public List<CategoryDto> all() {
        return repo.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public CategoryDto one(Long id) {
        Category category = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Категория не найдена"));
        return toDto(category);
    }

    public CategoryDto create(CategoryCreateDto dto) {

        if (repo.existsByName(dto.getName())) {
            throw new EntityNotFoundException("Категория с таким названием уже существует");
        }

        Category category = new Category();
        category.setName(dto.getName());

        repo.save(category);
        return toDto(category);
    }

    public CategoryDto update(Long id, CategoryUpdateDto dto) {
        Category category = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Категория не найдена"));

        category.setName(dto.getName());
        repo.save(category);

        return toDto(category);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    private CategoryDto toDto(Category c) {
        CategoryDto dto = new CategoryDto();
        dto.setId(c.getId());
        dto.setName(c.getName());
        return dto;
    }
}
