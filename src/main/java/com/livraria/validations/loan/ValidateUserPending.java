package com.livraria.validations.loan;

import com.livraria.config.exception.BusinessRuleValidation;
import com.livraria.model.Loan;
import com.livraria.repository.LoanRepository;
import com.livraria.validations.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ValidateUserPending implements Validator<Loan> {

    private final LoanRepository loanRepository;

    @Override
    public void validate(Loan loan) {
        List<Loan> loansUserPeding = loanRepository.findAllByLibraryUser_IdAndActualReturnDateIsNull(loan.getLibraryUser().getId());
        if (loansUserPeding.size() >= 3) {
            throw new BusinessRuleValidation("Limite de empréstimos excedido pelo usuário.");
        }
    }
}