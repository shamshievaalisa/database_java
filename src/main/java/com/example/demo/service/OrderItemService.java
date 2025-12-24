package com.example.demo.service;

import com.example.demo.dto.OrderItemCreateDto;
import com.example.demo.dto.OrderItemDto;
import com.example.demo.dto.OrderItemUpdateDto;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.model.Product;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepo;
    private final OrderRepository orderRepo;
    private final ProductRepository productRepo;

    public List<OrderItemDto> all() {
        return orderItemRepo.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public OrderItemDto one(Long id) {
        OrderItem item = orderItemRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Позиция заказа не найдена"));
        return toDto(item);
    }

    public OrderItemDto create(OrderItemCreateDto dto) {
        Order order = orderRepo.findById(dto.getOrderId())
                .orElseThrow(() -> new EntityNotFoundException("Заказ не найден"));

        Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Товар не найден"));

        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(dto.getQuantity());
        item.setPriceAtMoment(dto.getPriceAtMoment());
        item.setStatus(dto.getStatus());

        orderItemRepo.save(item);
        return toDto(item);
    }

    public OrderItemDto update(Long id, OrderItemUpdateDto dto) {
        OrderItem item = orderItemRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Позиция заказа не найдена"));

        item.setQuantity(dto.getQuantity());
        item.setPriceAtMoment(dto.getPriceAtMoment());
        item.setStatus(dto.getStatus());

        orderItemRepo.save(item);
        return toDto(item);
    }

    public void delete(Long id) {
        orderItemRepo.deleteById(id);
    }

    private OrderItemDto toDto(OrderItem item) {
        OrderItemDto dto = new OrderItemDto();
        dto.setId(item.getId());
        dto.setOrderId(item.getOrder().getId());
        dto.setProductId(item.getProduct().getId());
        dto.setQuantity(item.getQuantity());
        dto.setPriceAtMoment(item.getPriceAtMoment());
        dto.setStatus(item.getStatus());
        return dto;
    }

    // === НОВЫЙ МЕТОД ===
    public List<OrderItemDto> findByOrderId(Long orderId) {
        return orderItemRepo.findByOrderId(orderId).stream()
                .map(this::toDto)
                .toList();
    }
}