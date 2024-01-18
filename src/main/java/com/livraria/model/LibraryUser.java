package com.livraria.model;

import com.livraria.model.dto.libraryUser.LibraryUserSaveDTO;
import com.livraria.model.dto.libraryUser.LibraryUserUpdateDTO;
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
public class LibraryUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private LocalDate dateOfBirth;

    public LibraryUser(LibraryUserSaveDTO dto) {
        this.name = dto.name();
        this.email = dto.email();
        this.dateOfBirth = dto.dateOfBirth();
    }

    public void update(LibraryUserUpdateDTO dto) {
        this.name = dto.name();
        this.email = dto.email();
        this.dateOfBirth = dto.dateOfBirth();
    }
}