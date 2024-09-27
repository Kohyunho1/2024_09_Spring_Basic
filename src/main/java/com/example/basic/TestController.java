package com.example.basic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final ArticleDao articleDao;

    @RequestMapping("/article/write")
    @ResponseBody
    public String write(String title, String body) {
        articleDao.write(title, body);

        return "게시물이 저장되었습니다.";
    }

    @RequestMapping("/article/list")
    @ResponseBody
    public List<Article> list() {
        List<Article> articleList = articleDao.list();

        return articleList;
    }

    @RequestMapping("/article/detail/{id}") // id는 결정될 수 없는 값이기 때문에 변수화한다.
    @ResponseBody
    public Article detail(@PathVariable("id") int id) { //@PathVarible("변수명") -> url에 포함된 정보를 메서드에서 사용 가능
        Article article = articleDao.detail(id);

        return article;
    }

    @RequestMapping("/article/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") int id) {
        articleDao.delete(id);

        return "게시글이 삭제되었습니다";
    }

    @RequestMapping("/article/modify/{id}")
    @ResponseBody // 리턴값을 html 코드로 전달
    public String modify(@PathVariable("id") int id, String title, String body) {

        // 빌더 방식 - 실수 확률이 적다
        Article article = Article.builder()
                .id(id)
                .title(title)
                .body(body)
                .build();

        articleDao.modify(article);

        return "게시글이 수정되었습니다";  // 브라우저 출력 => html 문자열로 출력
    }

    @RequestMapping("article/write2")
    @ResponseBody
    public String write2(String title, String body) {

        // 코드 정리 단축키 -> 컨트롤 + 알트 + L
        Article article = Article.builder()
                .title(title)
                .body(body)
                .build();

        articleDao.write2(article);

        return "게시글이 작성되었습니다!";
    }

    @RequestMapping("/show-html")
    public String showHtml() {
        return "/test"; // .html 확장자를 스프링부트가 자동으로 붙여줌
    }

    @RequestMapping("/show-write")
    public String showWrite() {
        return "write";
    }
}
