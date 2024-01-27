package com.livraria.repository;

import com.livraria.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    Optional<Loan> findByIdAndActualReturnDateIsNull(Long idLoan);

    List<Loan> findAllByLibraryUser_IdAndActualReturnDateIsNull(Long idUser);
}