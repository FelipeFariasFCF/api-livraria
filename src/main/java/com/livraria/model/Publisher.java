package com.livraria.model;

import com.livraria.model.dto.PublisherSaveDTO;
import com.livraria.model.dto.PublisherUpdateDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer foundationYear;

    public Publisher(PublisherSaveDTO dto) {
        this.name = dto.name();
        this.foundationYear = dto.foundationYear();
    }

    public void update(PublisherUpdateDTO dto) {
        this.name = dto.name();
        this.foundationYear = dto.foundationYear();
    }
}