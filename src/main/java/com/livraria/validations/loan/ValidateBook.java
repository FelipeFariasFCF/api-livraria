package com.livraria.validations.loan;

import com.livraria.model.Book;
import com.livraria.model.Loan;
import com.livraria.service.BookService;
import com.livraria.validations.Validator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidateBook implements Validator<Loan> {

    private final BookService bookService;

    @Override
    public void validate(Loan loan) {
        try {
            Book book = bookService.findById(loan.getBook().getId());
            loan.setBook(book);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Erro ao associar Livro: " + e.getMessage());
        }
    }
}