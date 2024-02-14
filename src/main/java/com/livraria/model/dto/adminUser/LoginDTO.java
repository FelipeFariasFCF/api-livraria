package com.livraria.model.dto.adminUser;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank String login,
        @NotBlank String password
) {
}