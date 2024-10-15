package com.example.basic.domain.auth.controller;

import com.example.basic.domain.auth.entity.Member;
import com.example.basic.global.ReqResHandler;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthController {

  private final ReqResHandler reqResHandler;
  private List<Member> meberList;

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
  public String login(@Valid LoginForm loginForm, HttpServletResponse response, HttpSession session) {

    List<Member> memberList = new ArrayList<>();

    Member member1 = Member.builder()
        .username("hong")
        .password("1234")
        .role("admin")
        .build();

    Member member2 = Member.builder()
        .username("kim")
        .password("qwer")
        .role("normal")
        .build();

    memberList.add(member1);
    memberList.add(member2);

    Member targetMember = null;

    for (Member member : memberList) {
      if (member.getUsername().equals(loginForm.username) || member.getPassword().equals(loginForm.password)) {
        targetMember = member;
        break;
      }
    }

    if (targetMember == null) {
      return "login-fail";
    }

    // 장부(세션)을 마련해서 저장해야함.
    // 장부에 사용자 아이디 적기
    session.setAttribute("loginUser", loginForm.username);
    // 장부에 사용자 권한 적기
    session.setAttribute("role", targetMember.getRole());

    // 회원을 구별하기 위한 회원번호는 쿠키로 발급되는데 => 장부를 사용하면 장부 제공자인 스프링부트가 알아서 해줌

    return "redirect:/article/list";
  }
}
