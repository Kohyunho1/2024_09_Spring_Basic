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
    @ResponseBody // 리턴값을 html 코드로 전달
    public String write(String title, String body) {
        articleDao.write(title, body);

        return "게시물이 저장되었습니다."; // 브라우저 출력 => html 문자열로 출력
    }

    @GetMapping("/article/list")
    public String articlelist(Model model) {

        List<Article> articleList = articleDao.list();

        model.addAttribute("articleList", articleList);

        return "article/list";
    }

    @RequestMapping("/article/detail/{id}") // id는 결정될 수 없는 값이기 때문에 변수화한다.
    public String detail(@PathVariable("id") int id, Model model) { //@PathVarible("변수명") -> url에 포함된 정보를 메서드에서 사용 가능

        Article articleDetail = articleDao.detail(id);

        model.addAttribute("articleDetail", articleDetail);

        return "article/detail";
    }

    @RequestMapping("/article/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") int id) {
        articleDao.delete(id);

        return "게시글이 삭제되었습니다";
    }

    @GetMapping("/article/modify")
    public String articleModify() {
        return "article/modify";
    }

    @PostMapping("/article/modify/{id}")
    @ResponseBody
    public String modify(@PathVariable("id") int id, String title, String body, Model model) {

        // 빌더 방식 - 실수 확률이 적다
        // 코드 정리 단축키 -> 컨트롤 + 알트 + L
        Article article = Article.builder()
                .id(id)
                .title(title)
                .body(body)
                .build();

        articleDao.modify(article);

        return "게시글이 수정되었습니다.";
    }

    @GetMapping("/fruits")
    public String showFruits() {
        return "fruits"; // .html 확장자를 스프링부트가 자동으로 붙여줌
    }
}
