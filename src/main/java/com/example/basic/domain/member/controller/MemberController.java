package com.example.basic.domain.member.controller;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.member.entity.Member;
import com.example.basic.domain.member.service.MemberService;
import com.example.basic.global.reqres.ReqResHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;
  private final ReqResHandler reqResHandler;

  @GetMapping("/info")
  public String info(Model model) {
    Member loginMember = reqResHandler.getLoginMember();
    List<Article> articleList = memberService.getArticlesByAuthor(loginMember);

    model.addAttribute("articleList", articleList);
    return "member/info";
  }
}
