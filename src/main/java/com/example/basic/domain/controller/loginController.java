package com.example.basic.domain.controller;

import com.example.basic.global.ReqResHandler;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class loginController {

  private final ReqResHandler reqResHandler;

  @GetMapping("/logout")
  public String logout(HttpServletRequest request, HttpServletResponse response) { // 매개변수 - request, response

    Cookie targetCookie = reqResHandler.getCookieByName(request, "loginUser");

    if (targetCookie != null) {
      targetCookie.setMaxAge(0);
      response.addCookie(targetCookie);
    }

    // 화면돌리기
    return "redirect:/article/list";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @Getter
  @Setter
  public static class LoginForm {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
  }

  @PostMapping("/login")
  public String login(@Valid LoginForm loginForm, HttpServletResponse response) {
    String dbUser = "hong";
    String dbpass = "1234";
    String dbRole = "admin"; // normal, admin


    //로그인 실패
    if (!dbUser.equals(loginForm.username) || !dbpass.equals(loginForm.password)) {
      return "login-fail";
    }

    // loginUser 쿠폰을 발행. 쿠폰 값은 username
    // 로그인 성공
    Cookie cookie = new Cookie("loginUser", loginForm.username);
    Cookie role = new Cookie("role", dbRole);

    // 쿠키의 유효 시간 설정 (초 단위, 1시간 유효) => 로그인 만료 시간
    cookie.setMaxAge(60 * 60);

    // 쿠키의 경로 설정 (도메인의 어느 경로에서 쿠키가 유효한지)
    cookie.setPath("/");

    // 응답에 쿠키 추가
    response.addCookie(cookie);
    response.addCookie(role);

    return "redirect:/article/list";
  }
}
