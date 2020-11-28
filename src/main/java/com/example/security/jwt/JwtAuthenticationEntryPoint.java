package com.example.security.jwt;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authenticationException) throws IOException {
        log.debug("Jwt authentication failed: " + authenticationException);
        responseJson(response);
    }

    private void responseJson(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type", "application/json; charset=UTF-8");
        JSONObject json = new JSONObject();
        json.put("code", HttpServletResponse.SC_UNAUTHORIZED);
        json.put("message", "Jwt authentication failed");
        response.getWriter().write(json.toJSONString());
    }
}
