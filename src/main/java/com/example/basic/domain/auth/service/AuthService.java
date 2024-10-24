package com.example.basic.domain.auth.service;

import com.example.basic.domain.member.entity.Member;
import com.example.basic.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final MemberService memberService;

  public Member getLoginMember(String username, String password) {

    Member member = memberService.getByUsernameOrNull(username);

    if (member == null) {
      throw new RuntimeException("없는 회원정보입니다.");
    }


    if (!member.getPassword().equals(password)) {
      throw new RuntimeException("잘못된 회원정보입니다.");
    }

    return member;
  }
}
