package com.example.common;


import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Result<T> implements Serializable {

  private static final long serialVersionUID = 1L;

  public static final int NO_LOGIN = -1;

  public static final int SUCCESS = 0;

  public static final int FAIL = 1;

  public static final int NO_PERMISSION = 2;


  private int code;

  private String message = "success";

  private LocalDateTime timestamp = LocalDateTime.now();

  private T data;

  public Result() {
    super();
  }

  public Result(T data) {
    super();
    this.data = data;
  }

  public Result(Throwable e) {
    super();
    this.code = FAIL;
    this.message = e.toString();
  }
}
