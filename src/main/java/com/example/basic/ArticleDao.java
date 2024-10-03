package com.example.basic;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleDao {

  // CRUD -> insert, select, update, delete

  void write(Article article);

  List<Article> list();

  Article detail(int id);

  void delete(int id);

  void modify(Article article);
}
