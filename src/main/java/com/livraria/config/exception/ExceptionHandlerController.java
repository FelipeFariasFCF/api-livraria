package com.livraria.config.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> bindException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        ValidationError error = new ValidationError(
                LocalDateTime.now(),
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation Error",
                "Erro na validação de atributos");

        ex.getBindingResult().getFieldErrors().forEach(e ->
                error.addError(e.getField(), e.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFoundException(EntityNotFoundException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .path(request.getRequestURI())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Not found")
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
        StandardError standardError = StandardError.builder()
                .path(request.getRequestURI())
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .error("Unprocessable entity")
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY.value()).body(standardError);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<StandardError> erroValidacaoCampos(ValidationException ex, HttpServletRequest request) {
        StandardError standardError = StandardError.builder()
                .path(request.getRequestURI())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad request")
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }
}