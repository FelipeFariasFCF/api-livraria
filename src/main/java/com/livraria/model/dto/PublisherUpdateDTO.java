package com.livraria.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PublisherUpdateDTO(
        @NotBlank String name,
        @NotNull Integer foundationYear
) {
}