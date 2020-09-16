package com.example.sevice.impl;

import com.example.common.BaseException;
import com.example.domain.UserInfo;
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
  public UserInfo signOn(UserInfo user) {
    if (users.existsByUsername(user.getUsername())) {
      String message = "Username [" + user.getUsername() + "] is already exist!";
      log.error(message);
      throw new BaseException(message);
    }
    user.setPassword(encoder.encode(user.getPassword()));
    return users.save(user);
  }


}
