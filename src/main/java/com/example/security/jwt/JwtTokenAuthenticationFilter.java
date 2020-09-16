package com.example.security.jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

  private JwtTokenProvider jwtTokenProvider;

  public JwtTokenAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp,
      FilterChain chain) throws ServletException, IOException {
    String token = jwtTokenProvider.resolveToken(req);
    if (token != null && jwtTokenProvider.validateToken(token)) {
      Authentication auth = jwtTokenProvider.getAuthentication(token);

      if (auth != null) {
        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    }

    chain.doFilter(req, resp);
  }
}
