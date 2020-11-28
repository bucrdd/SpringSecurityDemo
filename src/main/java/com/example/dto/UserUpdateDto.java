package com.example.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateDto {

    String Id;

    @NotBlank(message = "用户名不能为空！")
    String username;

    String password;


}
