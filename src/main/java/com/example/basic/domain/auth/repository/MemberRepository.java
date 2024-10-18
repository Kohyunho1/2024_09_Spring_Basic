package com.example.basic.domain.auth.repository;

import com.example.basic.domain.auth.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// <엔티티 클래스 이름, @Id 필드의 데이터 타입>
public interface MemberRepository extends JpaRepository<Member, Long> {

  // JPA가 자동으로 만들어주는 메서드 시그니쳐가 있고 개발자가 직접 만들어야 하는 메서드 시그니쳐가 있음
  // save, delete, deleteById, findById, finAll ...

  // where 필터링의 경우 findBy 컬럼명
  // 결과가 다건이면 List<엔터티>
  // 결과가 단건이면 Optional<엔터티>
  Optional<Member> findByUsername(String username);
}
