package com.livraria.model.dto.book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record BookStockAddDTO(
        @NotNull Long idBook,
        @NotNull @Min(1) Integer quantity
) {
}