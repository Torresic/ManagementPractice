package com.example.management.exception;

import com.example.management.model.User;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /*WIP - Según stackoverflow el "about:blank" que devuelve el tipo es debido a que el error
    Se ejecuta en un contexto diferente al que estamos tratando ***INVESTIGAR SOLUCION*** */
    
    @ExceptionHandler(value = UserValidationException.class)
    protected ResponseEntity<Object> handleUserValidation(RuntimeException e,UserValidationException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse();
        error.setType(ex.getClass().getName());
        error.setTitle(ex.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setDetail("An unexpected error occurred");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Error: " + ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

}


