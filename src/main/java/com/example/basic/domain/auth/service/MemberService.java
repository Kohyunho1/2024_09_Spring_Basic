package com.example.basic.domain.auth.service;

import com.example.basic.domain.auth.entity.Member;
import com.example.basic.domain.auth.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  public Member getLoginMember(String username, String password) {

    // 아이디로 회원 정보 찾기
    Optional<Member> memberOpt = memberRepository.findByUsername(username); // repository에서 id를 제외한 필드(컬럼)으로 필터링

    if (memberOpt.isEmpty()) {
      throw new RuntimeException("잘못된 회원정보입니다.");
    }

    // 비밀번호 일치 여부 확인
    Member member = memberOpt.get();

    if (!member.getPassword().equals(password)) {
      throw new RuntimeException("잘못된 회원정보입니다.");
    }

    return member;
  }
}
