package com.livraria.service;

import com.livraria.config.exception.BusinessRuleValidation;
import com.livraria.model.Book;
import com.livraria.model.dto.book.BookStockAddDTO;
import com.livraria.model.dto.book.BookUpdateDTO;
import com.livraria.repository.BookRepository;
import com.livraria.validations.Validator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final List<Validator<Book>> validators;

    @Transactional
    public Book save(Book book) {
        validators.forEach(v -> v.validate(book));
        return bookRepository.save(book);
    }

    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Book findById(Long idBook) {
        return bookRepository.findById(idBook).orElseThrow(() -> new EntityNotFoundException("Livro não encontrado."));
    }

    public Book findByIdAvailable(Long idBook) {
        return bookRepository.findByIdAndAvailableQuantityGreaterThanZero(idBook).orElseThrow(() -> new BusinessRuleValidation("Livro indisponível para empréstimo."));
    }

    @Transactional
    public Book update(Long idBook, BookUpdateDTO dto) {
        Book book = this.findById(idBook);
        book.update(dto);
        return this.save(book);
    }

    @Transactional
    public void delete(Long idBook) {
        this.findById(idBook);
        bookRepository.deleteById(idBook);
    }

    @Transactional
    public Book addStock(BookStockAddDTO dto) {
        Book book = this.findById(dto.idBook());
        book.addStockBook(dto.quantity());
        return book;
    }

    @Transactional
    public void returnBook(Book book) {
        book.addAvailable();
    }
}