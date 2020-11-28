package com.example.controller;

import com.example.common.BaseException;
import com.example.common.Result;
import com.example.domain.UserInfo;
import com.example.repository.UserRepository;
import com.example.sevice.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
@Slf4j
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
    @ApiOperation(value = "用户列表")
    public Result<List<UserInfo>> findAllUsers(String username) {
        return new Result<>(users.findAllByUsernameLike("%" + (username == null ? "" : username) + "%"));
    }

    @PostMapping("/sign_on")
    @ApiOperation(value = "注册用户")
    public Result<UserInfo> signOn(@Validated @RequestBody UserInfo user, BindingResult errors) {
        if (errors.hasErrors()) {
            StringBuilder build = new StringBuilder();
            for (FieldError error : errors.getFieldErrors()) {
                build.append(error.getField()).append(": ").append(error.getDefaultMessage());
            }
            log.error("user [{}] can not sign on with errors: {}", user.getUsername(), build.toString());
            throw new BaseException(build.toString());
        }

        UserInfo userInfo = userService.signOn(user);
        log.info("User [{}] signed on", userInfo.getUsername());
        return new Result<>(userInfo);
    }

    @GetMapping("/me")
    public Result<UserInfo> me(@AuthenticationPrincipal UserInfo userInfo) {
        return new Result<>(userInfo);
    }
}
