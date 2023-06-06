package com.example.screensservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MvcHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exception(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
