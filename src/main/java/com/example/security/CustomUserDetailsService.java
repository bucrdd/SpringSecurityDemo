package com.example.security;

import com.example.entity.UserInfo;
import com.example.repository.UserRepository;
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
