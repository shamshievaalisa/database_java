package com.example.demo.dto;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String login;
    private String password;
    private String fullName;
    private String position;
    private String role;
}
