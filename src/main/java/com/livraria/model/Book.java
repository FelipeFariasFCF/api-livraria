package com.livraria.model;

import com.livraria.model.dto.book.BookSaveDTO;
import com.livraria.model.dto.book.BookUpdateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String isbn;
    private Integer publicationYear;
    private Integer availableQuantity;
    private Integer totalQuantity;

    @ManyToOne
    private Publisher publisher = new Publisher();

    @ManyToOne
    private Author author = new Author();

    public Book(BookSaveDTO dto) {
        this.title = dto.title();
        this.isbn = dto.isbn();
        this.publicationYear = dto.publicationYear();
        this.availableQuantity = 0;
        this.totalQuantity = 0;
        this.publisher.setId(dto.idPublisher());
        this.author.setId(dto.idAuthor());
    }

    public void update(BookUpdateDTO dto) {
        this.title = dto.title();
        this.isbn = dto.isbn();
        this.publicationYear = dto.publicationYear();
        this.publisher.setId(dto.idPublisher());
        this.author.setId(dto.idAuthor());
    }

    public void addStockBook(Integer quantity) {
        this.totalQuantity += quantity;
        this.availableQuantity += quantity;
    }
}