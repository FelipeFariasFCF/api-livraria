package com.livraria.model.dto.author;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record AuthorSaveDTO(
        @NotBlank String name,
        @NotNull @Past LocalDate dateOfBirth
) {
}