package com.example.basic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {

  private final ArticleDao articleDao;

  //    주소창에 입력한 url은 기본적으로 GET 방식
  @GetMapping("/article/write")
  public String articlewrite() {
    return "article/write";
  }

  @PostMapping("/article/write")
  public String write(String title, String body, Model model) {

    // 코드 정리 단축키 -> 컨트롤 + 알트 + L
    Article article = Article.builder()
        .title(title)
        .body(body)
        .build();

    articleDao.write(article);
    return "redirect:/article/list"; // redirect 뒤에 적는 것은 url을 적는 것. 템플릿 이름 아님. 주소창을 해당 url로 바꾸라는 의미
  }

  @GetMapping("/article/list")
  public String articlelist(Model model) {

    List<Article> articleList = articleDao.list();

    model.addAttribute("articleList", articleList);

    return "article/list";
  }

  @RequestMapping("/article/detail/{id}") // id는 결정될 수 없는 값이기 때문에 변수화한다.
  public String detail(@PathVariable("id") int id, Model model) { //@PathVarible("변수명") -> url에 포함된 정보를 메서드에서 사용 가능

    Article article = articleDao.detail(id);

    model.addAttribute("article", article);

    return "article/detail";
  }

  @RequestMapping("/article/delete/{id}")
  public String delete(@PathVariable("id") int id) {

    articleDao.delete(id);

    return "redirect:/article/list";
  }

  @PostMapping("/article/modify/{id}")
  public String modify(@PathVariable("id") int id, String title, String body, Model model) {

    // 빌더 방식 - 실수 확률이 적다
    Article article = Article.builder()
        .id(id)
        .title(title)
        .body(body)
        .build();

    articleDao.modify(article);

    model.addAttribute("article", article);

    return "redirect:/article/list";
  }

  @GetMapping("/fruits")
  public String showFruits() {
    return "fruits"; // .html 확장자를 스프링부트가 자동으로 붙여줌
  }
}
