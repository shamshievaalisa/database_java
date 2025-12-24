package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id_позиции

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id")
    private Order order; // id_заказа (FK)

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product; // id_товара (FK)

    @Column(nullable = false)
    private Integer quantity; // количество

    @Column(nullable = false)
    private BigDecimal priceAtMoment; // цена_на_момент

    @Column(nullable = false)
    private String status; // статус заказа (по позиции, например: NEW, IN_PROGRESS, DONE, CANCELED)
}
