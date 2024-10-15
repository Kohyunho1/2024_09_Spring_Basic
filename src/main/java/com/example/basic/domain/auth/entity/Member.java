package com.example.basic.domain.auth.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
  private String username;
  private String password;
  private String role;

}
