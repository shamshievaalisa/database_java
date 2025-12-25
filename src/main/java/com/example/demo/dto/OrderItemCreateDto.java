package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemCreateDto {

    @NotNull(message = "ID заказа обязателен")
    @Positive(message = "ID заказа должен быть положительным числом")
    private Long orderId;

    @NotNull(message = "ID товара обязателен")
    @Positive(message = "ID товара должен быть положительным числом")
    private Long productId;

    @NotNull(message = "Количество обязательно")
    @Min(value = 1, message = "Количество должно быть не меньше 1")
    @Max(value = 10000, message = "Количество не может превышать 10000")
    private Integer quantity;

    @NotNull(message = "Цена обязательна")
    @DecimalMin(value = "0.01", message = "Цена должна быть положительной")
    @Digits(integer = 10, fraction = 2, message = "Цена может содержать до 10 цифр до запятой и 2 после")
    private BigDecimal priceAtMoment;

    @NotBlank(message = "Статус обязателен")
    @Pattern(
            regexp = "^(CREATED|CONFIRMED|CANCELLED|COMPLETED)$",
            message = "Недопустимый статус. Допустимые значения: CREATED, CONFIRMED, CANCELLED, COMPLETED"
    )
    private String status;
}