package com.livraria.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer foundationYear;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books;
}
