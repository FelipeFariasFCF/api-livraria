package com.livraria.service;

import com.livraria.model.Loan;
import com.livraria.repository.LoanRepository;
import com.livraria.validations.loan.ValidatorLoan;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final List<ValidatorLoan> validators;

    @Transactional
    public Loan bookLoan(Loan loan) {
        validators.forEach(v -> v.validate(loan));
        return loanRepository.save(loan);
    }
}