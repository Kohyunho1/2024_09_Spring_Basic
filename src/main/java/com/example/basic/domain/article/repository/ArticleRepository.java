package com.example.basic.domain.article.repository;

import com.example.basic.domain.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, String> {
  // 저장 -> save

  // 단건 조회 -> findById

  // 전체 조회 -> findAll

  // 삭제 -> delete, deleteById
}
