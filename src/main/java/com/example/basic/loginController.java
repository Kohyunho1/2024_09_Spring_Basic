package com.example.basic;

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

    //로그인 실패
    if (!dbUser.equals(loginForm.username) || !dbpass.equals(loginForm.password)) {
      return "login-fail";
    }

    // loginUser 쿠폰을 발행. 쿠폰 값은 username
    Cookie cookie = new Cookie("loginUser", loginForm.username);

    cookie.setMaxAge(60 * 60);

    cookie.setPath("/");

    response.addCookie(cookie);

    //로그인 성공
    return "redirect:/article/list";
  }

  @GetMapping("/logout")
  public String logout(HttpServletRequest request, HttpServletResponse response) {
    // 클라이언트가 보낸 모든 쿠키를 가져옴
    Cookie[] cookies = request.getCookies();

    if (cookies != null) {
      for (Cookie cookie : cookies) {
        // 삭제하려는 쿠키의 이름을 확인 (예: "myCookie")
        if ("loginUser".equals(cookie.getName())) {
          // 쿠키 삭제: 같은 이름과 경로로 쿠키를 생성하되, 만료 시간을 0으로 설정
          Cookie deleteCookie = new Cookie("loginUser", null);
          deleteCookie.setMaxAge(0);  // 쿠키 만료
          deleteCookie.setPath("/");  // 해당 경로에 대한 쿠키 삭제

          // 응답에 삭제된 쿠키 추가
          response.addCookie(deleteCookie);
          return "쿠키가 삭제되었습니다.";
        }
      }
    }

    return "해당 쿠키가 존재하지 않습니다.";
  }
}
