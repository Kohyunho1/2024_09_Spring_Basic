package com.example.basic.domain.article.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Article {

  @Id
  private Long id;
  private String title;
  private String body;
}