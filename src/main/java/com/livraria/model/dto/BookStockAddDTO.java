package com.livraria.model.dto;

import jakarta.validation.constraints.NotNull;

public record BookStockAddDTO(
        @NotNull Long idBook,
        @NotNull Integer quantity
) {
}