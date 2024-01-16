package com.livraria.validations.book;

import com.livraria.config.exception.ValidationException;
import com.livraria.model.Author;
import com.livraria.model.Book;
import com.livraria.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookValidateAuthor implements ValidatorBook{

    private final AuthorService authorService;

    @Override
    public void validate(Book book) {
        try {
            Author author = authorService.findById(book.getAuthor().getId());
            book.setAuthor(author);
        } catch (ValidationException e) {
            throw new ValidationException("Erro ao associar Autor: " + e.getMessage());
        }
    }
}