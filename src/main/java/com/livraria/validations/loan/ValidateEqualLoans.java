package com.livraria.validations.loan;

import com.livraria.config.exception.BusinessRuleValidation;
import com.livraria.model.Loan;
import com.livraria.repository.LoanRepository;
import com.livraria.validations.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidateEqualLoans implements Validator<Loan> {

    private final LoanRepository loanRepository;

    @Override
    public void validate(Loan loan) {
        Boolean exists = loanRepository.existsByLibraryUser_IdAndBook_IdAndActualReturnDateIsNull(loan.getLibraryUser().getId(), loan.getBook().getId());
        if (exists) {
            throw new BusinessRuleValidation("Usuário com empréstimo igual pendente.");
        }
    }
}