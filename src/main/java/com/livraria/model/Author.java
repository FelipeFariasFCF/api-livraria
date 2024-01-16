package com.livraria.model;

import com.livraria.model.dto.AuthorSaveDTO;
import com.livraria.model.dto.AuthorUpdateDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate dateOfBirth;

    public Author(AuthorSaveDTO dto) {
        this.name = dto.name();
        this.dateOfBirth = dto.dateOfBirth();
    }

    public void update(AuthorUpdateDTO dto) {
        this.name = dto.name();
        this.dateOfBirth = dto.dateOfBirth();
    }
}