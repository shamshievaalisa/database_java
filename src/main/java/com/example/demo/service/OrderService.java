package com.example.demo.service;

import com.example.demo.dto.OrderCreateDto;
import com.example.demo.dto.OrderDto;
import com.example.demo.dto.OrderUpdateDto;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.Client;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepo;
    private final ClientRepository clientRepo;
    private final UserRepository userRepo;

    public List<OrderDto> all() {
        return orderRepo.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public OrderDto one(Long id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Заказ не найден"));
        return toDto(order);
    }

    public OrderDto create(OrderCreateDto dto) {
        Client client = clientRepo.findById(dto.getClientId())
                .orElseThrow(() -> new EntityNotFoundException("Клиент не найден"));

        User employee = userRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Сотрудник не найден"));

        Order order = new Order();
        order.setDate(dto.getDate());
        order.setClient(client);
        order.setEmployee(employee);

        orderRepo.save(order);
        return toDto(order);
    }

    public OrderDto update(Long id, OrderUpdateDto dto) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Заказ не найден"));

        Client client = clientRepo.findById(dto.getClientId())
                .orElseThrow(() -> new EntityNotFoundException("Клиент не найден"));

        User employee = userRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Сотрудник не найден"));

        order.setDate(dto.getDate());
        order.setClient(client);
        order.setEmployee(employee);

        orderRepo.save(order);
        return toDto(order);
    }

    public void delete(Long id) {
        orderRepo.deleteById(id);
    }

    private OrderDto toDto(Order o) {
        OrderDto dto = new OrderDto();
        dto.setId(o.getId());
        dto.setDate(o.getDate());
        dto.setClientId(o.getClient().getId());
        dto.setEmployeeId(o.getEmployee().getId());
        return dto;
    }
}
