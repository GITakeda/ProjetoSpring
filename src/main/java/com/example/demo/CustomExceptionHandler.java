package com.example.demo;

import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.RestException;
import com.example.demo.exception.WrongArgumentException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler({NotFoundException.class})
    protected ResponseEntity<Object> handleAll(NotFoundException ex) {
        return new ResponseEntity(new RestException(HttpStatus.NOT_FOUND, ex.getMessage()), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({WrongArgumentException.class})
    protected ResponseEntity<Object> handleAll(WrongArgumentException ex) {
        return new ResponseEntity(new RestException(HttpStatus.BAD_REQUEST, ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
