package com.example.demo.service;

import com.example.demo.dto.ProductCreateDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductUpdateDto;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;

    public List<ProductDto> all() {
        return productRepo.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public ProductDto one(Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Товар не найден"));
        return toDto(product);
    }

    public ProductDto create(ProductCreateDto dto) {
        Category category = categoryRepo.findById(dto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Категория не найдена"));

        Product p = new Product();
        p.setName(dto.getName());
        p.setPrice(dto.getPrice());
        p.setCategory(category);

        productRepo.save(p);
        return toDto(p);
    }

    public ProductDto update(Long id, ProductUpdateDto dto) {
        Product p = productRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Товар не найден"));

        Category category = categoryRepo.findById(dto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Категория не найдена"));

        p.setName(dto.getName());
        p.setPrice(dto.getPrice());
        p.setCategory(category);

        productRepo.save(p);
        return toDto(p);
    }

    public void delete(Long id) {
        productRepo.deleteById(id);
    }

    private ProductDto toDto(Product p) {
        ProductDto dto = new ProductDto();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setPrice(p.getPrice());
        dto.setCategoryId(p.getCategory().getId());
        return dto;
    }
}
