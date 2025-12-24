package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "clients")
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id_клиента

    @Column(nullable = false)
    private String fullName; // ФИО

    @Column(nullable = false)
    private String phone; // телефон
}
