package com.livraria.model.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookSaveDTO(
        @NotBlank String title,
        @NotBlank String isbn,
        @NotNull Integer publicationYear,
        @NotNull Long idPublisher,
        @NotNull Long idAuthor
) {
}