package com.example.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

  @ExceptionHandler(value = {BaseException.class})
  public Result<Object> baseException(BaseException e, WebRequest request) {
    log.debug(e.getMessage());
    return new Result<>(e);
  }

  @ExceptionHandler(value = {RuntimeException.class})
  public Result<Object> runTimeException(RuntimeException e, WebRequest request) {
    e.printStackTrace();
    return new Result<>(e);
  }

}
