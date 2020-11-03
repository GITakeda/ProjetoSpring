package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class WrongArgumentException extends RuntimeException{
    public WrongArgumentException(String message){
        super(message);
    }
}
