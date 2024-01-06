package com.livraria.model.dto;

import com.livraria.model.Author;

import java.time.LocalDate;

public record AuthorDetailsDTO(
        Long id,
        String name,
        LocalDate dateOfBirth
) {
    public AuthorDetailsDTO(Author author) {
        this(author.getId(), author.getName(), author.getDateOfBirth());
    }
}