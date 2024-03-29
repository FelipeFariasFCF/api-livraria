package com.livraria.service;

import com.livraria.model.Publisher;
import com.livraria.model.dto.publisher.PublisherUpdateDTO;
import com.livraria.repository.PublisherRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublisherService {

    private final PublisherRepository publisherRepository;

    @Transactional
    public Publisher save(Publisher publisher) {
        publisher.setName(publisher.getName().toUpperCase());
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