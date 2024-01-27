package com.livraria.validations.loan;

import com.livraria.model.Book;
import com.livraria.model.Loan;
import com.livraria.service.BookService;
import com.livraria.validations.Validator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidateBookStock implements Validator<Loan> {

    private final BookService bookService;

    @Override
    @Transactional
    public void validate(Loan loan) {
        Book book = bookService.findByIdAvailable(loan.getBook().getId());
        book.removeAvailable();
    }
}