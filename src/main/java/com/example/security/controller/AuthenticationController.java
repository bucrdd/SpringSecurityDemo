package com.example.security.controller;

import static org.springframework.http.ResponseEntity.ok;

import com.example.security.entity.UserInfo;
import com.example.security.security.CustomUserDetailsService;
import com.example.security.security.jwt.JwtTokenProvider;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  @Resource
  private AuthenticationManager authenticationManager;

  @Resource
  private JwtTokenProvider tokenProvider;

  @Resource
  private CustomUserDetailsService users;

  @PostMapping("/login")
  public ResponseEntity<Map<Object, Object>> login(@RequestBody AuthenticationRequest data) {
    try {
      String username = data.getUsername();
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));

      UserInfo user = users.loadUserByUsername(username);
      String token = tokenProvider.createToken(username, user.getRoles());
      Map<Object, Object> model = new HashMap<>();
      model.put("username", username);
      model.put("token", token);
      return ok(model);
    } catch (AuthenticationException e) {
      throw new BadCredentialsException("Invalid username/password supplied");
    }
  }
}
