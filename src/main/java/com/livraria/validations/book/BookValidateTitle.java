package com.livraria.validations.book;

import com.livraria.config.exception.ValidationException;
import com.livraria.model.Book;
import com.livraria.repository.BookRepository;
import com.livraria.validations.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookValidateTitle implements Validator<Book> {

    private final BookRepository bookRepository;


    @Override
    public void validate(Book book) {
        if (book.getId() == null) {
            if (bookRepository.existsByTitleIgnoreCase(book.getTitle())) {
                throw new ValidationException("Livro " + book.getTitle() + " já cadastrado no sistema.");
            }
        }

        Optional<Book> bookOptional = bookRepository.findByTitleIgnoreCase(book.getTitle());
        if (bookOptional.isPresent() && !Objects.equals(bookOptional.get().getId(), book.getId())) {
            throw new ValidationException("Livro " + book.getTitle() + " já cadastrado no sistema.");
        }
    }
}