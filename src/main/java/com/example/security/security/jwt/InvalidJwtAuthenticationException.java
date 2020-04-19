package com.example.security.security.jwt;


import org.springframework.security.core.AuthenticationException;

public class InvalidJwtAuthenticationException extends AuthenticationException {

  private static final long serialVersionId = 1L;

  public InvalidJwtAuthenticationException(String msg) {
    super(msg);
  }

}
