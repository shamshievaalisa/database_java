package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderUpdateDto {
    private LocalDate date;
    private Long clientId;
    private Long employeeId;
}
