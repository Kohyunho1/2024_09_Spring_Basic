package com.example.basic.domain.comment.entity;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.auth.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String body;

  @ManyToOne
  private Article article;

  @ManyToOne
  @JoinColumn(name = "author_id")
  private Member author;

}
