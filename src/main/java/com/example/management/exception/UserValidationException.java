package com.example.management.exception;

public class UserValidationException extends IllegalArgumentException {
    public UserValidationException(String message) {
        super(message);
    }
}