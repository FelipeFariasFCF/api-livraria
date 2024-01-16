package com.livraria.controller;

import com.livraria.model.Publisher;
import com.livraria.model.dto.PublisherDetailsDTO;
import com.livraria.model.dto.PublisherSaveDTO;
import com.livraria.model.dto.PublisherUpdateDTO;
import com.livraria.service.PublisherService;
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
@RequestMapping("/v1/editoras")
public class PublisherController {

    private final PublisherService publisherService;

    @PostMapping
    public ResponseEntity<PublisherDetailsDTO> save(@Valid @RequestBody PublisherSaveDTO dto, UriComponentsBuilder uriComponentsBuilder) {
        Publisher publisher = publisherService.save(new Publisher(dto));
        URI uri = uriComponentsBuilder.path("/v1/editoras/{id}").buildAndExpand(publisher.getId()).toUri();
        return ResponseEntity.created(uri).body(new PublisherDetailsDTO(publisher));
    }

    @GetMapping
    public ResponseEntity<Page<PublisherDetailsDTO>> findAll(@PageableDefault(sort = "name") Pageable pageable) {
        return ResponseEntity.ok().body(publisherService.findAll(pageable).map(PublisherDetailsDTO::new));
    }

    @GetMapping("/{idPublisher}")
    public ResponseEntity<PublisherDetailsDTO> findById(@PathVariable Long idPublisher) {
        return ResponseEntity.ok(new PublisherDetailsDTO(publisherService.findById(idPublisher)));
    }

    @PutMapping("/{idPublisher}")
    public ResponseEntity<PublisherDetailsDTO> update(@PathVariable Long idPublisher, @Valid @RequestBody PublisherUpdateDTO dto) {
        return ResponseEntity.ok(new PublisherDetailsDTO(publisherService.update(idPublisher, dto)));
    }

    @DeleteMapping("/{idPublisher}")
    public ResponseEntity<Void> delete(@PathVariable Long idPublisher) {
        publisherService.delete(idPublisher);
        return ResponseEntity.noContent().build();
    }
}