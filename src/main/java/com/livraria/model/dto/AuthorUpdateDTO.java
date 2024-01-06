package com.livraria.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AuthorUpdateDTO(
        @NotBlank String name,
        @NotNull LocalDate dateOfBirth
) {
}