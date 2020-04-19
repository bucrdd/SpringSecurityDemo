package com.example.security.security;

import com.example.security.entity.Role;
import com.example.security.entity.UserInfo;
import java.util.List;
import javax.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class CustomUserDetailsServiceTest {

  @Resource
  private CustomUserDetailsService users;

  @Test
  void loadUserByUsername() {
    Assertions.assertEquals("TEST_USER", users.loadUserByUsername("TEST_USER").getUsername());
  }
}