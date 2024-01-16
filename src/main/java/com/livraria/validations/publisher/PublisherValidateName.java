package com.livraria.validations.publisher;

import com.livraria.config.exception.ValidationException;
import com.livraria.model.Publisher;
import com.livraria.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PublisherValidateName implements ValidatorPublisher {

    private final PublisherRepository publisherRepository;

    @Override
    public void validate(Publisher publisher) {
        if (publisher.getId() == null) {
            if (publisherRepository.existsByNameIgnoreCase(publisher.getName())) {
                throw new ValidationException("Editora " + publisher.getName() + " já cadastrado no sistema.");
            }
        }

        Optional<Publisher> publisherOptional = publisherRepository.findByNameIgnoreCase(publisher.getName());
        if (publisherOptional.isPresent() && !Objects.equals(publisherOptional.get().getId(), publisher.getId())) {
            throw new ValidationException("Editora " + publisher.getName() + " já cadastrado no sistema.");
        }
    }
}
