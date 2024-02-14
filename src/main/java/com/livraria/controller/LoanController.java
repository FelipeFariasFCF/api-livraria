package com.livraria.controller;

import com.livraria.model.Loan;
import com.livraria.model.dto.loan.BookLoanDTO;
import com.livraria.model.dto.loan.LoanDetailsDTO;
import com.livraria.service.LoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/emprestimos")
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public ResponseEntity<LoanDetailsDTO> bookLoan(@Valid @RequestBody BookLoanDTO dto, UriComponentsBuilder uriComponentsBuilder) {
        Loan loan = loanService.bookLoan(new Loan(dto));
        URI uri = uriComponentsBuilder.path("/v1/emprestimos/{id}").buildAndExpand(loan.getId()).toUri();
        return ResponseEntity.created(uri).body(new LoanDetailsDTO(loan));
    }

    @PutMapping("/{idLoan}")
    public ResponseEntity<Void> returnLoan(@PathVariable Long idLoan) {
        loanService.returnLoan(idLoan);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pendentes")
    public ResponseEntity<List<LoanDetailsDTO>> outstandingLoans() {
        return ResponseEntity.ok().body(loanService.outstandingLoans().stream().map(LoanDetailsDTO::new).toList());
    }

    @GetMapping("/livros/{idBook}")
    public ResponseEntity<List<LoanDetailsDTO>> findAllLoansByBook(@PathVariable Long idBook) {
        return ResponseEntity.ok().body(loanService.findAllLoansByBook(idBook).stream().map(LoanDetailsDTO::new).toList());
    }

    @GetMapping("/usuarios/{idUser}")
    public ResponseEntity<List<LoanDetailsDTO>> findAllLoansByUser(@PathVariable Long idUser) {
        return ResponseEntity.ok().body(loanService.findAllLoansByUser(idUser).stream().map(LoanDetailsDTO::new).toList());
    }
}