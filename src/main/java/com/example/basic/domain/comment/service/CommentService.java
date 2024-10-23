package com.example.basic.domain.comment.service;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.article.service.ArticleService;
import com.example.basic.domain.auth.entity.Member;
import com.example.basic.domain.comment.entity.Comment;
import com.example.basic.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
  private final CommentRepository commentRepository;
  private final ArticleService articleService;



  public void write(String body, long articleId, Member author) {

    Article article = articleService.getById(articleId);
    Comment c1 = Comment.builder()
        .body(body)
        .article(article)
        .author(author)
        .build();

    commentRepository.save(c1);
  }

  public void update(long commentId, String body) {
    Comment comment = getById(commentId);
    comment.setBody(body);
    commentRepository.save(comment);
  }

  public Comment getById(long id) {
    Optional<Comment> commentOpt = commentRepository.findById(id);

    if (commentOpt.isEmpty()) {
      throw new RuntimeException("없는 데이터입니다.");
    }

    return commentOpt.get();
  }

  public void deleteById(long commentId) {
    commentRepository.deleteById(commentId);
  }
}
