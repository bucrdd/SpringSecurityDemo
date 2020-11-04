package com.example.security.jwt;

import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;


@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authenticationException) throws IOException {
    log.debug("Jwt authentication failed: " + authenticationException);
    JSONObject json = new JSONObject();
    json.put("code", HttpServletResponse.SC_UNAUTHORIZED);
    json.put("message", "Jwt authentication failed");
    response.getWriter().write(json.toJSONString());
  }
}
