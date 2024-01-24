package com.livraria.model.dto.loan;

import jakarta.validation.constraints.NotNull;

public record BookLoanDTO(
        @NotNull Long idBook,
        @NotNull Long idUser
) {
}