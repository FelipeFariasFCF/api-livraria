package com.livraria.validations.loan;

import com.livraria.config.exception.BusinessRuleValidation;
import com.livraria.model.Book;
import com.livraria.model.Loan;
import com.livraria.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoanValidateBookStock implements ValidatorLoan {

    private final BookService bookService;

    @Override
    public void validate(Loan loan) {
        Book book = bookService.findById(loan.getBook().getId());
        if (book.getAvailableQuantity() <= 0) {
            throw new BusinessRuleValidation("Livro indisponível para empréstimo.");
        }
        book.removeAvailable();
    }
}