package com.example.controller;

import com.example.common.Result;
import com.example.domain.Role;
import com.example.repository.UserRepository;
import com.example.security.jwt.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Api(tags = "鉴权")
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private UserRepository users;


  @PostMapping("/login")
  @ApiOperation("用户登录")
  public Result<Map<String, Object>> signIn(@RequestBody AuthenticationRequest data) {
    try {
      String username = data.getUsername();
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
      List<Role> roles = users.findByUsername(username)
          .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found")).getRoles();
      String token = jwtTokenProvider.createToken(username, roles.stream().map(Role::getRole).collect(Collectors.toList()));

      Map<String, Object> model = new HashMap<>();
      model.put("username", username);
      model.put("token", token);
      return new Result<>(model);
    } catch (AuthenticationException e) {
      throw new BadCredentialsException("Invalid username/password supplied");
    }
  }

}
