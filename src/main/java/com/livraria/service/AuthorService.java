package com.livraria.service;

import com.livraria.model.Author;
import com.livraria.model.dto.author.AuthorUpdateDTO;
import com.livraria.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Transactional
    public Author save(Author author) {
        author.setName(author.getName().toUpperCase());
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