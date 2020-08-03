package com.example.config.exception;


import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.ResponseEntity.status;

import com.example.security.jwt.InvalidJwtAuthenticationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(value = {InvalidJwtAuthenticationException.class})
  public ResponseEntity<Throwable> invalidJwtAuthenticationException(InvalidJwtAuthenticationException e,
      WebRequest request) {
    return status(UNAUTHORIZED).build();
  }

}
