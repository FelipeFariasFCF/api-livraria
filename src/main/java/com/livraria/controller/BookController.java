package com.livraria.controller;

import com.livraria.model.Book;
import com.livraria.model.dto.book.BookDetailsDTO;
import com.livraria.model.dto.book.BookSaveDTO;
import com.livraria.model.dto.book.BookStockAddDTO;
import com.livraria.model.dto.book.BookUpdateDTO;
import com.livraria.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/livros")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookDetailsDTO> save(@Valid @RequestBody BookSaveDTO dto, UriComponentsBuilder uriComponentsBuilder) {
        Book book = bookService.save(new Book(dto));
        URI uri = uriComponentsBuilder.path("/v1/livros/{id}").buildAndExpand(book.getId()).toUri();
        return ResponseEntity.created(uri).body(new BookDetailsDTO(book));
    }

    @GetMapping
    public ResponseEntity<Page<BookDetailsDTO>> findAll(@PageableDefault(sort = "title") Pageable pageable) {
        return ResponseEntity.ok(bookService.findAll(pageable).map(BookDetailsDTO::new));
    }

    @GetMapping("/{idBook}")
    public ResponseEntity<BookDetailsDTO> findById(@PathVariable Long idBook) {
        return ResponseEntity.ok(new BookDetailsDTO(bookService.findById(idBook)));
    }

    @PutMapping("/{idBook}")
    public ResponseEntity<BookDetailsDTO> update(@PathVariable Long idBook, @Valid @RequestBody BookUpdateDTO dto) {
        return ResponseEntity.ok(new BookDetailsDTO(bookService.update(idBook, dto)));
    }

    @DeleteMapping("/{idBook}")
    public ResponseEntity<Void> delete(@PathVariable Long idBook) {
        bookService.delete(idBook);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/add-estoque")
    public ResponseEntity<BookDetailsDTO> bookStockAdd(@Valid @RequestBody BookStockAddDTO dto) {
        return ResponseEntity.ok(new BookDetailsDTO(bookService.addStock(dto)));
    }
}