package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id_заказа

    @Column(nullable = false)
    private LocalDate date; // дата заказа

    // FK -> Client
    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id")
    private Client client;

    // FK -> User (сотрудник)
    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id")
    private User employee;
}
