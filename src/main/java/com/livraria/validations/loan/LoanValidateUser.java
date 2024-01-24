package com.livraria.validations.loan;

import com.livraria.model.LibraryUser;
import com.livraria.model.Loan;
import com.livraria.service.LibraryUserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoanValidateUser implements ValidatorLoan {

    private final LibraryUserService libraryUserService;

    @Override
    public void validate(Loan loan) {
        try {
            LibraryUser user = libraryUserService.findById(loan.getLibraryUser().getId());
            loan.setLibraryUser(user);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Erro ao associar Usuário: " + e.getMessage());
        }
    }
}