package com.livraria.model;

import jakarta.persistence.*;

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
    private Publisher publisher;

    @ManyToOne
    private Author author;
}