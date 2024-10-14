package com.example.basic.domain.article.service;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.article.dao.ArticleDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

  private final ArticleDao articleDao;

  // 1. 기능 구현
  // 2. 유지 보수를 생각한 코드
  public Article getById(long id) {
    Article article = articleDao.findById(id); // 데이터 처리(비지니스 로직)

    return article;
  }

  public List<Article> getAll() {

    return articleDao.findAll();
  }

  public void write(String title, String body) {
    // 코드 정리 단축키 -> 컨트롤 + 알트 + L
    Article article = Article.builder()
        .title(title)
        .body(body)
        .build();

    articleDao.save(article);
  }

  public void deleteById(long id) {
    articleDao.deleteById(id);
  }

  public void update(long id, String title, String body) {
    // 빌더 방식
    Article article = Article.builder()
        .id(id)
        .title(title)
        .body(body)
        .build();

    articleDao.update(article);
  }
}