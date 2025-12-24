package com.example.demo.controller;

import com.example.demo.dto.OrderCreateDto;
import com.example.demo.dto.OrderDto;
import com.example.demo.dto.OrderUpdateDto;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @GetMapping
    public List<OrderDto> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public OrderDto one(@PathVariable Long id) {
        return service.one(id);
    }

    @PostMapping
    public OrderDto create(@RequestBody OrderCreateDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public OrderDto update(@PathVariable Long id,
                           @RequestBody OrderUpdateDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "deleted";
    }
}
