package com.livraria.model;

import com.livraria.model.dto.AuthorSaveDTO;
import com.livraria.model.dto.AuthorUpdateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate dateOfBirth;

    @OneToMany
    private List<Book> books;

    public Author(AuthorSaveDTO dto) {
        this.name = dto.name();
        this.dateOfBirth = dto.dateOfBirth();
    }

    public void update(AuthorUpdateDTO dto) {
        this.name = dto.name().trim();
        this.dateOfBirth = dto.dateOfBirth();
    }
}