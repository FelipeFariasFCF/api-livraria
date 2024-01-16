package com.livraria.service;

import com.livraria.model.Author;
import com.livraria.model.dto.AuthorUpdateDTO;
import com.livraria.repository.AuthorRepository;
import com.livraria.validations.author.ValidatorAuthor;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final List<ValidatorAuthor> validators;

    @Transactional
    public Author save(Author author) {
        validators.forEach(v -> v.validate(author));
        return authorRepository.save(author);
    }

    public Page<Author> findAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    public Author findById(Long idAuthor) {
        return authorRepository.findById(idAuthor).orElseThrow(() -> new EntityNotFoundException("Autor não encontrado."));
    }

    @Transactional
    public Author update(Long idAuthor, AuthorUpdateDTO dto) {
        Author author = this.findById(idAuthor);
        author.update(dto);
        return this.save(author);
    }

    @Transactional
    public void delete(Long idAuthor) {
        this.findById(idAuthor);
        authorRepository.deleteById(idAuthor);
    }
}