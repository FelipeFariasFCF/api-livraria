package com.livraria.model.dto.tag;

import com.livraria.model.Tag;

public record TagDetailsDTO(
        Long id,
        String name
) {
    public TagDetailsDTO(Tag tag) {
        this(tag.getId(), tag.getName());
    }
}
