package com.livraria.validations.book;

import com.livraria.model.Author;
import com.livraria.model.Book;
import com.livraria.service.AuthorService;
import com.livraria.validations.Validator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookValidateAuthor implements Validator<Book> {

    private final AuthorService authorService;

    @Override
    public void validate(Book book) {
        try {
            Author author = authorService.findById(book.getAuthor().getId());
            book.setAuthor(author);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Erro ao associar Autor: " + e.getMessage());
        }
    }
}