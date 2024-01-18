package com.livraria.model.dto.libraryUser;

import com.livraria.model.LibraryUser;

import java.time.LocalDate;

public record LibraryUserDetailsDTO(
        Long id,
        String name,
        String email,
        LocalDate dateOfBirth
) {
    public LibraryUserDetailsDTO(LibraryUser user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getDateOfBirth());
    }
}