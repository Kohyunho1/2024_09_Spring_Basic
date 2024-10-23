package com.example.basic.domain.article.entity;

import com.example.basic.domain.auth.entity.Member;
import com.example.basic.domain.comment.entity.Comment;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Article {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String body;
//  private Long memberId; // 회원 번호

  @ManyToOne
  @JoinColumn(name = "author_id")
  private Member author;

  @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
  List<Comment> commentList = new ArrayList<>();

}