package com.example.basic.domain.auth.dao;

import com.example.basic.domain.auth.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
  void save(Member member);

}
