package com.example.DesafioProfissional.controllers.exceptions;

import com.example.DesafioProfissional.services.exceptions.PersonagemException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(PersonagemException.class)
    public ResponseEntity<StandardError> personagemException(PersonagemException ex, HttpServletRequest request) {
        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NO_CONTENT.value(), ex.getMessage(),
                "Ação Inválida! ", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
