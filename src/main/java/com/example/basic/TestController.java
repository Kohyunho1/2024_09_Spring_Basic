package com.example.basic;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TestController {


  @GetMapping("/find-cookie")
  @ResponseBody
  public String findCookie(HttpServletRequest request) {

    // 모든 쿠키를 가져옴
    Cookie[] cookies = request.getCookies();


      for (Cookie cookie : cookies) {
        if (cookies.equals(cookie.getName())) {
          return "쿠키 값: " + cookie.getValue();
        }
      }


    return "쿠키값";
  }


  @GetMapping("/cookie-test")
  @ResponseBody
  public String cookieTest(HttpServletResponse response) {
    Cookie cookie = new Cookie("test", "1234");
    // 쿠키의 유효 시간 설정 (초 단위, 1시간 유효)
    cookie.setMaxAge(60 * 60);
    // 쿠키의 경로 설정 (도메인의 어느 경로에서 쿠키가 유효한지)
    cookie.setPath("/");
    // 응답에 쿠키 추가
    response.addCookie(cookie);
    return "쿠키를 발행하였습니다.";
  }

  @GetMapping("/test/var")
  public String var() {
    return "test/variable";
  }

  @GetMapping("/test/con")
  public String num() {
    return "test/condition";
  }

  @GetMapping("/test/loop")
  public String loop() {
    return "test/loop";
  }

  @GetMapping("/test/param")
  public String param(Model model) {

    int myNumber = 10;
    String myString = "hello";

    List<String> fruits = new ArrayList<>();

    fruits.add("banana");
    fruits.add("orange");
    fruits.add("apple");

//    Article article = articleDao.findById(1L);

    model.addAttribute("myNumber", myNumber);
    model.addAttribute("myString", myString);
    model.addAttribute("fruits", fruits);
//    model.addAttribute("article", article);

    return "test/param";
  }
}
