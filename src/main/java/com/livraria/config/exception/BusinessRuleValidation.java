package com.livraria.config.exception;

public class BusinessRuleValidation extends RuntimeException {
    public BusinessRuleValidation(String message) {
        super(message);
    }
}