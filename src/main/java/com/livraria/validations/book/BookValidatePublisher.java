package com.livraria.validations.book;

import com.livraria.model.Book;
import com.livraria.model.Publisher;
import com.livraria.service.PublisherService;
import com.livraria.validations.Validator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookValidatePublisher implements Validator<Book> {

    private final PublisherService publisherService;

    @Override
    public void validate(Book book) {
        try {
            Publisher publisher = publisherService.findById(book.getPublisher().getId());
            book.setPublisher(publisher);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Erro ao associar Editora: " + e.getMessage());
        }
    }
}