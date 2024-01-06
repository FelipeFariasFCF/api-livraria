package com.livraria.validations;

public interface Validator<T> {
    void validate(T object);
}