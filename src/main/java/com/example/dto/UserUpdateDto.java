package com.example.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserUpdateDto {

  String Id;

  @NotBlank(message = "用户名不能为空！")
  String username;

  String password;


}
