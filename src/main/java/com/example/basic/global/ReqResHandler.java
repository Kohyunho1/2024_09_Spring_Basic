package com.example.basic.global;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class ReqResHandler {
  public Cookie getLoginCookie(HttpServletRequest request) {

    Cookie[] cookies = request.getCookies();
    Cookie targetCookie = null;

    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("loginUser")) {
          targetCookie = cookie;
        }
      }
    }
    return targetCookie;
  }
}
