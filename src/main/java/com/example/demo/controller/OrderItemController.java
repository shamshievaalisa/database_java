package com.example.demo.controller;

import com.example.demo.dto.OrderItemCreateDto;
import com.example.demo.dto.OrderItemDto;
import com.example.demo.dto.OrderItemUpdateDto;
import com.example.demo.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid; // ⚠️ Импорт для @Valid
import java.util.List;

@RestController
@RequestMapping("/order-items")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService service;

    @GetMapping
    public List<OrderItemDto> all(@RequestParam(required = false) Long orderId) {
        if (orderId != null) {
            return service.findByOrderId(orderId);
        }
        return service.all();
    }

    @GetMapping("/{id}")
    public OrderItemDto one(@PathVariable Long id) {
        return service.one(id);
    }

    @PostMapping
    public OrderItemDto create(@Valid @RequestBody OrderItemCreateDto dto) { // ✅
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public OrderItemDto update(@PathVariable Long id,
                               @Valid @RequestBody OrderItemUpdateDto dto) { // ✅
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "deleted";
    }
}