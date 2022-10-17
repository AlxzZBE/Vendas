package com.alex.spring.vendas.handler;

import com.alex.spring.vendas.exceptions.ArgumentNotValidException;
import com.alex.spring.vendas.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundException(NotFoundException ex) {
        return new ResponseEntity<>(new ExceptionDetails(
                ex, "Not Found Exception, check the details and developer message.",
                "Probably the resource that you're searched doesn't exists."), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ArgumentNotValidException.class)
    public ResponseEntity<ExceptionDetails> handlerArgumentNotValidException(ArgumentNotValidException ex) {
        return new ResponseEntity<>(new ExceptionDetails(
                ex, "Argument Not Valid Exception, check the details and developer message.",
                "Probably the argument doesn't match with the acceptable."), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDetails> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(new ExceptionDetails(
                ex, "Method Argument Not Valid Exception, check the details and errors."), ex.getStatusCode());
    }
}