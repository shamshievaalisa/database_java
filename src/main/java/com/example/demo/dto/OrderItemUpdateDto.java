package com.example.demo.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemUpdateDto {

    @Min(value = 1, message = "Количество должно быть не меньше 1")
    @Max(value = 10000, message = "Количество не может превышать 10000")
    private Integer quantity;

    @DecimalMin(value = "0.01", message = "Цена должна быть положительной")
    @Digits(integer = 10, fraction = 2, message = "Цена может содержать до 10 цифр до запятой и 2 после")
    private BigDecimal priceAtMoment;

    @Pattern(
            regexp = "^(CREATED|CONFIRMED|CANCELLED|COMPLETED)$",
            message = "Недопустимый статус. Допустимые значения: CREATED, CONFIRMED, CANCELLED, COMPLETED"
    )
    private String status;
}