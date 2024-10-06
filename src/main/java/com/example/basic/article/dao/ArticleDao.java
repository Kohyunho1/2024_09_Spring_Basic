package com.example.basic.article.dao;

import com.example.basic.article.entity.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleDao {
  void save(Article article);

  List<Article> findAll();

  Article findById(Long id);

  void deleteById(Long id);

  // void update(@Param("id") Long id, @Param("title") String title, @Param("body") String body);
  void update(Article article);
}