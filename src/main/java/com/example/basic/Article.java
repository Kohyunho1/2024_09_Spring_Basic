package com.example.basic;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Article {
    private int id;
    private String title;
    private String body;
}
