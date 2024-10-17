package com.example.basic.domain.auth.repository;

import com.example.basic.domain.auth.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

// <엔티티 클래스 이름, @Id 필드의 데이터 타입>
public interface MemberRepository extends JpaRepository<Member, String> {
  // 저장 -> save

  // 단건 조회 -> findById

  // 전체 조회 -> findAll

  // 삭제 -> delete, deleteById
}
