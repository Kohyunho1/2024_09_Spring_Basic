package com.example.basic.domain.admin.controller;

import com.example.basic.global.ReqResHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

  private final ReqResHandler reqResHandler;

  @GetMapping("/stat")
  public String stat(HttpSession session) {

    return "admin/stat";
  }

  @GetMapping("/user")
  public String user(HttpSession session) {

    return "admin/user";
  }

  @GetMapping("/main")
  public String main(HttpServletRequest request, Model model, HttpSession session) {

    return "/admin/main";
  }
}
