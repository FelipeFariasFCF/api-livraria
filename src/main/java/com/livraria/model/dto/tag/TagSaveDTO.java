package com.livraria.model.dto.tag;

import jakarta.validation.constraints.NotBlank;

public record TagSaveDTO(
        @NotBlank String name
) {
}