package com.example.basic;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleDao {

    // CRUD -> insert, select, update, delete

    void write(@Param("title") String title, @Param("body") String body);

    List<Article> list();

    Article detail(int id);

    void delete(int id);

    void modify(Article article);
}
