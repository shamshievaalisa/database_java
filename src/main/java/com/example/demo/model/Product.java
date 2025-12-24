package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id_товара

    @Column(nullable = false)
    private String name; // название

    @Column(nullable = false)
    private BigDecimal price; // цена

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private Category category; // FK на категорию
}
