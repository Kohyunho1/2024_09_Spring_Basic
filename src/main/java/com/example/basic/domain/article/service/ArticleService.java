package com.example.basic.domain.article.service;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.article.repository.ArticleRepository;
import com.example.basic.domain.auth.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

  private final ArticleRepository articleRepository;

  public Article getById(long id) {

    Optional<Article> articleOpt = articleRepository.findById(id);

    if(articleOpt.isEmpty()) {
      throw new RuntimeException("존재하지 않는 게시물입니다.");
    }

    Article article = articleOpt.get();
    return article;
  }

  public List<Article> getAll() {
    return articleRepository.findAll();
  }

  public void write(String title, String body, Member author) {

    Article article = Article.builder()
        .title(title)
        .body(body)
        .author(author)
        .build();

    articleRepository.save(article);
  }

  public void deleteById(long id) {
    articleRepository.deleteById(id);
  }

  public void update(long id, String title, String body) {

    Article article = getById(id);

    article.setTitle(title);
    article.setBody(body);

    articleRepository.save(article);
  }
}