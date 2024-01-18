package com.livraria.model.dto.publisher;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PublisherSaveDTO(
        @NotBlank String name,
        @NotNull Integer foundationYear
) {
}