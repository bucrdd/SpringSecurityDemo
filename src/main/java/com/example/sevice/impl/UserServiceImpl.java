package com.example.sevice.impl;

import com.example.common.BaseException;
import com.example.domain.UserInfo;
import com.example.dto.UserUpdateDto;
import com.example.repository.UserRepository;
import com.example.sevice.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository users;

  @Autowired
  private PasswordEncoder encoder;

  @Override
  public UserInfo signOn(UserUpdateDto param) {
    if (users.existsByUsername(param.getUsername())) {
      throw new BaseException(param.getUsername() + " is already exists!");
    }
    UserInfo user = UserInfo.builder()
        .username(param.getUsername())
        .password(encoder.encode(param.getPassword()))
        .build();
    return users.save(user);

  }

}
