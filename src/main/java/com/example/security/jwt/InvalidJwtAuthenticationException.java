package com.example.security.jwt;

import org.springframework.security.core.AuthenticationException;

public class InvalidJwtAuthenticationException extends AuthenticationException {

  private static final Long serialVersionUID = -1L;

  public InvalidJwtAuthenticationException(String msg) {
    super(msg);
  }
}
