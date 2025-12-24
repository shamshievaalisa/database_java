package com.example.demo.exception;

// НЕ импортируйте ничего лишнего
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}