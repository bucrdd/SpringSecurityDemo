package com.example.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CookieController {

  @GetMapping("/test/cookie")
  public String testCookie(@RequestParam("browser") String browser, HttpServletRequest request,
      HttpServletResponse response, HttpSession session) {

    Object sessionBrowser = session.getAttribute("browser");
    if (sessionBrowser != null) {
      System.out.println("GET: Session.browser = " + sessionBrowser.toString());
    } else {
      System.out.println("SET: Session.browser = " + browser);
      session.setAttribute("browser", browser);
    }
    if (request.getCookies() != null && request.getCookies().length > 0) {
      Arrays.asList(request.getCookies()).forEach(
          cookie -> System.out.println(cookie.getName() + ": " + cookie.getValue())
      );
    }
    return "index";
  }
}
