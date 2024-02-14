package com.livraria.validations.book;

import com.livraria.model.Book;
import com.livraria.model.Tag;
import com.livraria.service.TagService;
import com.livraria.validations.Validator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookValidateTag implements Validator<Book> {

    private final TagService tagService;

    @Override
    public void validate(Book book) {
        try {
            Tag tag = tagService.findById(book.getTag().getId());
            book.setTag(tag);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Erro ao associar Etiqueta: " + e.getMessage());
        }
    }
}