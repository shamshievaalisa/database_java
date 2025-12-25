package com.example.demo.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderCreateDto {

    @NotNull(message = "Дата заказа обязательна")
    @FutureOrPresent(message = "Дата заказа не может быть в прошлом")
    private LocalDate date;

    @NotNull(message = "ID клиента обязателен")
    @Positive(message = "ID клиента должен быть положительным числом")
    private Long clientId;

    @NotNull(message = "ID сотрудника обязателен")
    @Positive(message = "ID сотрудника должен быть положительным числом")
    private Long employeeId;
}