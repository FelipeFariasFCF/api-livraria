package com.livraria.service;

import com.livraria.model.Publisher;
import com.livraria.model.dto.PublisherUpdateDTO;
import com.livraria.repository.PublisherRepository;
import com.livraria.validations.ValidatorPublisher;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final List<ValidatorPublisher> validators;

    @Transactional
    public Publisher save(Publisher publisher) {
        validators.forEach(v -> v.validate(publisher));
        return publisherRepository.save(publisher);
    }

    public Page<Publisher> findAll(Pageable pageable) {
        return publisherRepository.findAll(pageable);
    }

    public Publisher findById(Long idPublisher) {
        return publisherRepository.findById(idPublisher).orElseThrow(() -> new EntityNotFoundException("Editora não encontrada."));
    }

    @Transactional
    public Publisher update(long idPublisher, PublisherUpdateDTO dto) {
        Publisher publisher = this.findById(idPublisher);
        publisher.update(dto);
        return this.save(publisher);
    }

    @Transactional
    public void delete(Long idPublisher) {
        this.findById(idPublisher);
        publisherRepository.deleteById(idPublisher);
    }
}