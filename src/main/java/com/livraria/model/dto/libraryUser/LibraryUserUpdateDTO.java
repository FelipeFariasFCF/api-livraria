package com.livraria.model.dto.libraryUser;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record LibraryUserUpdateDTO(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotNull @Past LocalDate dateOfBirth
) {
}