package com.livraria.controller;

import com.livraria.model.LibraryUser;
import com.livraria.model.dto.libraryUser.LibraryUserDetailsDTO;
import com.livraria.model.dto.libraryUser.LibraryUserSaveDTO;
import com.livraria.model.dto.libraryUser.LibraryUserUpdateDTO;
import com.livraria.service.LibraryUserService;
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
@RequestMapping("/v1/usuarios")
public class LibraryUserController {

    private final LibraryUserService libraryUserService;

    @PostMapping
    public ResponseEntity<LibraryUserDetailsDTO> save(@Valid @RequestBody LibraryUserSaveDTO dto, UriComponentsBuilder uriComponentsBuilder) {
        LibraryUser user = libraryUserService.save(new LibraryUser(dto));
        URI uri = uriComponentsBuilder.path("/v1/usuarios/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new LibraryUserDetailsDTO(user));
    }

    @GetMapping
    public ResponseEntity<Page<LibraryUserDetailsDTO>> findAll(@PageableDefault(sort = "name") Pageable pageable) {
        return ResponseEntity.ok().body(libraryUserService.findAll(pageable).map(LibraryUserDetailsDTO::new));
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<LibraryUserDetailsDTO> findById(@PathVariable Long idUser) {
        return ResponseEntity.ok(new LibraryUserDetailsDTO(libraryUserService.findById(idUser)));
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<LibraryUserDetailsDTO> update(@PathVariable Long idUser, @Valid @RequestBody LibraryUserUpdateDTO dto) {
        return ResponseEntity.ok(new LibraryUserDetailsDTO(libraryUserService.update(idUser, dto)));
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<LibraryUserDetailsDTO> delete(@PathVariable Long idUser) {
        libraryUserService.delete(idUser);
        return ResponseEntity.noContent().build();
    }
}