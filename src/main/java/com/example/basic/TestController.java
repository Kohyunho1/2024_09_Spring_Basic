package com.example.basic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TestController {

  private final ArticleDao articleDao;

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

    Article article = articleDao.detail(4);

    model.addAttribute("myNumber", myNumber);
    model.addAttribute("myString", myString);
    model.addAttribute("fruits", fruits);
    model.addAttribute("article", article);

    return "test/param";
  }
}
