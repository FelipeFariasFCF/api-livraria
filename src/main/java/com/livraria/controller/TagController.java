package com.livraria.controller;

import com.livraria.model.Tag;
import com.livraria.model.dto.tag.TagDetailsDTO;
import com.livraria.model.dto.tag.TagSaveDTO;
import com.livraria.service.TagService;
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
@RequestMapping("/v1/etiquetas")
public class TagController {

    private final TagService tagService;

    @PostMapping
    public ResponseEntity<TagDetailsDTO> save(@Valid @RequestBody TagSaveDTO dto, UriComponentsBuilder uriComponentsBuilder) {
        Tag tag = tagService.save(new Tag(dto));
        URI uri = uriComponentsBuilder.path("/v1/etiquetas/{id}").buildAndExpand(tag.getId()).toUri();
        return ResponseEntity.created(uri).body(new TagDetailsDTO(tag));
    }

    @GetMapping
    public ResponseEntity<Page<TagDetailsDTO>> findAll(@PageableDefault(sort = "name") Pageable pageable) {
        return ResponseEntity.ok(tagService.findAll(pageable).map(TagDetailsDTO::new));
    }

    @DeleteMapping("/{idTag}")
    public ResponseEntity<Void> delete(@PathVariable Long idTag) {
        tagService.delete(idTag);
        return ResponseEntity.noContent().build();
    }
}
