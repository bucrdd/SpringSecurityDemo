package com.example.security.security;

import com.example.security.entity.UserInfo;
import com.example.security.repository.UserRepository;
import javax.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

  @Resource
  private UserRepository users;

  @Override
  public UserInfo loadUserByUsername(String username) throws UsernameNotFoundException {
    UserInfo user = users.findByUsername(username);
    if (user != null) {
      return user;
    }
    throw new UsernameNotFoundException("Username: " + username + " not found");
  }

}
