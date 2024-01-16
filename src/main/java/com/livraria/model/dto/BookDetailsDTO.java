package com.livraria.model.dto;

import com.livraria.model.Book;

public record BookDetailsDTO(
        Long id,
        String title,
        String isbn,
        Integer publicationYear,
        Integer availableQuantity,
        Integer totalQuantity,
        String publisher,
        String author
) {
    public BookDetailsDTO(Book book) {
        this(book.getId(), book.getTitle(), book.getIsbn(), book.getPublicationYear(), book.getAvailableQuantity(), book.getTotalQuantity(), book.getPublisher().getName(), book.getAuthor().getName());
    }
}