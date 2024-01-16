package com.livraria.validations.author;

import com.livraria.config.exception.ValidationException;
import com.livraria.model.Author;
import com.livraria.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorValidateName implements ValidatorAuthor {

    private final AuthorRepository authorRepository;

    @Override
    public void validate(Author author) {
        if (author.getId() == null) {
            if (authorRepository.existsByNameIgnoreCase(author.getName())) {
                throw new ValidationException("Autor " + author.getName() + " já cadastrado no sistema.");
            }
        }

        Optional<Author> authorOptional = authorRepository.findByNameIgnoreCase(author.getName());
        if (authorOptional.isPresent() && !Objects.equals(authorOptional.get().getId(), author.getId())) {
            throw new ValidationException("Autor " + author.getName() + " já cadastrado no sistema.");
        }
    }
}
