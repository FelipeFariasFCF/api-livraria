package com.livraria.controller;

import com.livraria.model.Author;
import com.livraria.model.dto.author.AuthorDetailsDTO;
import com.livraria.model.dto.author.AuthorSaveDTO;
import com.livraria.model.dto.author.AuthorUpdateDTO;
import com.livraria.service.AuthorService;
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
@RequestMapping("/v1/autores")
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorDetailsDTO> save(@Valid @RequestBody AuthorSaveDTO dto, UriComponentsBuilder uriComponentsBuilder) {
        Author author = authorService.save(new Author(dto));
        URI uri = uriComponentsBuilder.path("/v1/autores/{id}").buildAndExpand(author.getId()).toUri();
        return ResponseEntity.created(uri).body(new AuthorDetailsDTO(author));
    }

    @GetMapping
    public ResponseEntity<Page<AuthorDetailsDTO>> findAll(@PageableDefault(sort = "name") Pageable pageable) {
        return ResponseEntity.ok().body(authorService.findAll(pageable).map(AuthorDetailsDTO::new));
    }

    @GetMapping("/{idAuthor}")
    public ResponseEntity<AuthorDetailsDTO> findById(@PathVariable Long idAuthor) {
        return ResponseEntity.ok(new AuthorDetailsDTO(authorService.findById(idAuthor)));
    }

    @PutMapping("/{idAuthor}")
    public ResponseEntity<AuthorDetailsDTO> update(@PathVariable Long idAuthor, @Valid @RequestBody AuthorUpdateDTO dto) {
       return ResponseEntity.ok(new AuthorDetailsDTO(authorService.update(idAuthor, dto)));
    }

    @DeleteMapping("/{idAuthor}")
    public ResponseEntity<Void> delete(@PathVariable Long idAuthor) {
        authorService.delete(idAuthor);
        return ResponseEntity.noContent().build();
    }
}