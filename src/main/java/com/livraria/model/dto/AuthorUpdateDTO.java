package com.livraria.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record AuthorUpdateDTO(
        @NotBlank String name,
        @NotNull @Past LocalDate dateOfBirth
) {
}