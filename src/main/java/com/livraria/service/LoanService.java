package com.livraria.service;

import com.livraria.model.Loan;
import com.livraria.repository.LoanRepository;
import com.livraria.validations.Validator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final List<Validator<Loan>> validators;
    private final BookService bookService;

    @Transactional
    public Loan bookLoan(Loan loan) {
        validators.forEach(v -> v.validate(loan));
        return loanRepository.save(loan);
    }

    @Transactional
    public void returnLoan(Long idLoan) {
        Loan loan = loanRepository.findByIdAndActualReturnDateIsNull(idLoan).orElseThrow(() -> new EntityNotFoundException("Empréstimo não localizado."));
        loan.setActualReturnDate(LocalDate.now());
        bookService.returnBook(loan.getBook());
    }

    public List<Loan> outstandingLoans() {
        return loanRepository.findAllByActualReturnDateIsNull();
    }

    public List<Loan> findAllLoansByBook(Long idBook) {
        return loanRepository.findAllByBook_Id(idBook);
    }

    public List<Loan> findAllLoansByUser(Long idUser) {
        return loanRepository.findAllByLibraryUser_Id(idUser);
    }
}