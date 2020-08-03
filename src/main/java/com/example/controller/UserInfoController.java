package com.example.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {


  @GetMapping("/me")
  public ResponseEntity<Map<String, Object>> currentUser(@AuthenticationPrincipal UserDetails userDetails) {
    Map<String, Object> model = new HashMap<>();
    model.put("username", userDetails.getUsername());
    model.put("roles", userDetails.getAuthorities()
        .stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
    return ok(model);
  }
}
