package com.livraria.model;

import com.livraria.model.dto.loan.BookLoanDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate loanDate;
    private LocalDate expectedReturnDate;
    private LocalDate actualReturnDate;

    @ManyToOne
    private Book book = new Book();

    @ManyToOne
    private LibraryUser libraryUser = new LibraryUser();

    public Loan(BookLoanDTO dto) {
        this.loanDate = LocalDate.now();
        this.expectedReturnDate = LocalDate.now().plusDays(15L);
        this.book.setId(dto.idBook());
        this.libraryUser.setId(dto.idUser());
    }
}