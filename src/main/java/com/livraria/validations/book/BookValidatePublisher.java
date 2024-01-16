package com.livraria.validations.book;

import com.livraria.config.exception.ValidationException;
import com.livraria.model.Book;
import com.livraria.model.Publisher;
import com.livraria.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookValidatePublisher implements ValidatorBook {

    private final PublisherService publisherService;

    @Override
    public void validate(Book book) {
        try {
            Publisher publisher = publisherService.findById(book.getPublisher().getId());
            book.setPublisher(publisher);
        } catch (ValidationException e) {
            throw new ValidationException("Erro ao associar Editora: " + e.getMessage());
        }
    }
}