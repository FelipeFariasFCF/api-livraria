package com.livraria.model.dto.publisher;

import com.livraria.model.Publisher;

public record PublisherDetailsDTO(
        Long id,
        String name,
        Integer foundationYear
) {
    public PublisherDetailsDTO(Publisher publisher) {
        this(publisher.getId(), publisher.getName(), publisher.getFoundationYear());
    }
}