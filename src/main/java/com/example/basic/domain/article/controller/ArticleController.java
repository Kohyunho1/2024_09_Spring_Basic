package com.example.basic.domain.article.controller;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.article.service.ArticleService;
import com.example.basic.domain.auth.entity.Member;
import com.example.basic.domain.comment.entity.Comment;
import com.example.basic.domain.comment.service.CommentService;
import com.example.basic.global.reqres.ReqResHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {

  private final ArticleService articleService;
  private final CommentService commentService;
  private final ReqResHandler reqResHandler;

  @RequestMapping("/article/detail/{id}")
  public String detail(@PathVariable("id") long id, Model model, HttpServletRequest request) {

    Article article = articleService.getById(id);
    List<Comment> commentList = commentService.getAll();
    model.addAttribute("article", article);
    model.addAttribute("commentList", commentList);

    return "article/detail";
  }

  @RequestMapping("/article/list")
  public String list(Model model, HttpServletRequest request, HttpSession session) {
    List<Article> articleList = articleService.getAll();

    model.addAttribute("articleList", articleList);

    return "article/list";
  }

  @GetMapping("/article/write")
  public String articleWrite(Model model, HttpServletRequest request, HttpSession session) {

    return "article/write";
  }

  @Getter
  @Setter
  public static class WriteForm {
    @NotBlank
    private String title;
    @NotBlank
    private String body;
  }

  @PostMapping("/article/write")
  public String write(@Valid WriteForm writeForm, Model model, HttpSession session) {

    Member loginMember = (Member)session.getAttribute("loginUser");

    articleService.write(writeForm.getTitle(), writeForm.getBody(), loginMember);
    return "redirect:/article/list"; // redirect 뒤에 적는 것은 url을 적는 것. 템플릿 이름 아님. 주소창을 해당 url로 바꾸라는 의미
  }

  @RequestMapping("/article/delete/{id}")
  public String delete(@PathVariable long id, HttpSession session) {

    articleService.deleteById(id);
    return "redirect:/article/list";
  }

  @Getter
  @Setter
  public static class ModifyForm {
    @NotBlank
    String title;
    @NotBlank
    String body;
  }

  @RequestMapping("/article/modify/{id}")
  public String modify(@PathVariable("id") long id, @Valid ModifyForm modifyForm, HttpSession session) {

    articleService.update(id, modifyForm.getTitle(), modifyForm.getBody());
    return "redirect:/article/detail/%d".formatted(id); // 브라우저 출력 => html 문자열로 출력
  }

  @RequestMapping("/show-html")
  public String showHtml() {
    return "test"; // .html 확장자를 스프링부트가 자동으로 붙여줌
  }
}