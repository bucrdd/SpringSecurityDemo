package com.example.controller;

import com.example.common.BaseException;
import com.example.common.Result;
import com.example.domain.UserInfo;
import com.example.dto.UserUpdateDto;
import com.example.repository.UserRepository;
import com.example.sevice.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository users;

  @GetMapping("/virtual")
  @ApiOperation(value = "获得一个虚拟用户", notes = "获得一个虚拟用户 notes")
  public UserInfo virtualUser() {
    UserInfo userInfo = new UserInfo();
    userInfo.setUsername("virtual User");
    userInfo.setPassword("123456");
    return userInfo;
  }

  @GetMapping("/list")
  public List<UserInfo> findAllUsers() {
    return new ArrayList<UserInfo>(users.findAll());
  }

  @PostMapping("/sign_on")
  @ApiOperation(value = "注册用户")
  public Result<UserInfo> signOn(@Validated @RequestBody UserUpdateDto param, BindingResult errors) {
    if (errors.hasErrors()) {
      StringBuilder build = new StringBuilder();
      for (FieldError error : errors.getFieldErrors()) {
        build.append(error.getField()).append(": ").append(error.getDefaultMessage());
      }
      throw new BaseException(build.toString());
    }
    return new Result<>(userService.signOn(param));
  }

}
