package com.livraria.service;

import com.livraria.model.Tag;
import com.livraria.repository.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    @Transactional
    public Tag save(Tag tag) {
        tag.setName(tag.getName().toUpperCase());
        return tagRepository.save(tag);
    }

    public Page<Tag> findAll(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    public Tag findById(Long idTag) {
        return tagRepository.findById(idTag).orElseThrow(() -> new EntityNotFoundException("Etiqueta não encontrada."));
    }

    @Transactional
    public void delete(Long idTag) {
        this.findById(idTag);
        tagRepository.deleteById(idTag);
    }
}