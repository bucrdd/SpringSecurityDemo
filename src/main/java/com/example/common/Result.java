package com.example.common;


import lombok.Data;

@Data
public class Result<T> {

  private int code;

  private String message;

  private T data;

  public Result(T data) {
    this.code = 200;
    this.message = "success";
    this.data = data;
  }

  public Result(int code, String message, T data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  public Result(Throwable e) {
    this.code = 500;
    this.message = e.getMessage();
  }
}
