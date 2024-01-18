package com.livraria.validations.libraryUser;

import com.livraria.config.exception.ValidationException;
import com.livraria.model.LibraryUser;
import com.livraria.repository.LibraryUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LibraryUserValidateEmail implements ValidatorLibraryUser {

    private final LibraryUserRepository libraryUserRepository;

    @Override
    public void validate(LibraryUser user) {
        if (user.getId() == null) {
            if (libraryUserRepository.existsByEmailIgnoreCase(user.getEmail())) {
                throw new ValidationException("Email " + user.getEmail() + " já cadastrado no sistema.");
            }
        }

        Optional<LibraryUser> userOptional = libraryUserRepository.findByEmailIgnoreCase(user.getEmail());
        if (userOptional.isPresent() && !Objects.equals(userOptional.get().getId(), user.getId())) {
            throw new ValidationException("Email " + user.getEmail() + " já cadastrado no sistema.");
        }
    }
}
