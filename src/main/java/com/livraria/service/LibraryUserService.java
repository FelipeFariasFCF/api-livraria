package com.livraria.service;

import com.livraria.model.LibraryUser;
import com.livraria.model.dto.libraryUser.LibraryUserUpdateDTO;
import com.livraria.repository.LibraryUserRepository;
import com.livraria.validations.libraryUser.ValidatorLibraryUser;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryUserService {

    private final LibraryUserRepository libraryUserRepository;
    private final List<ValidatorLibraryUser> validators;

    @Transactional
    public LibraryUser save(LibraryUser user) {
        validators.forEach(v -> v.validate(user));
        return libraryUserRepository.save(user);
    }

    public Page<LibraryUser> findAll(Pageable pageable) {
        return libraryUserRepository.findAll(pageable);
    }

    public LibraryUser findById(Long idUser) {
        return libraryUserRepository.findById(idUser).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));
    }

    @Transactional
    public LibraryUser update(long idUser, LibraryUserUpdateDTO dto) {
        LibraryUser user = this.findById(idUser);
        user.update(dto);
        return this.save(user);
    }

    @Transactional
    public void delete(Long idUser) {
        this.findById(idUser);
        libraryUserRepository.deleteById(idUser);
    }
}