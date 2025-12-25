package com.example.demo.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderUpdateDto {

    @FutureOrPresent(message = "Дата не может быть в прошлом")
    private LocalDate date;

    @Positive(message = "ID клиента должен быть положительным числом")
    private Long clientId;

    @Positive(message = "ID сотрудника должен быть положительным числом")
    private Long employeeId;
}