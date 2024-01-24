package com.livraria.model.dto.loan;

import com.livraria.model.Loan;

import java.time.LocalDate;

public record LoanDetailsDTO(
        Long id,
        LocalDate loanDate,
        LocalDate expectedReturnDate,
        String book,
        String user
) {
    public LoanDetailsDTO(Loan loan) {
        this(loan.getId(), loan.getLoanDate(), loan.getExpectedReturnDate(), loan.getBook().getTitle(), loan.getLibraryUser().getName());
    }
}