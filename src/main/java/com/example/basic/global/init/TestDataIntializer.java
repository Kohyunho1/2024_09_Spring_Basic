package com.example.basic.global.init;

import com.example.basic.domain.article.entity.Article;
import com.example.basic.domain.article.repository.ArticleRepository;
import com.example.basic.domain.auth.entity.Member;
import com.example.basic.domain.auth.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataIntializer implements ApplicationRunner {

  private final MemberRepository memberRepository;
  private final ArticleRepository articleRepository;


  @Override
  public void run(ApplicationArguments args) throws Exception {
    System.out.println("====== 테스트 데이터 생성 ====== ");

    Member m1 = makeTestMemeber("hong", "1234", "admin");
    Member m2 = makeTestMemeber("kim", "qwer", "normal");

    memberRepository.save(m1);
    memberRepository.save(m2);

    Article a1 = makeTestArticle("제목1", "내용1", m1);
    Article a2 = makeTestArticle("제목2", "내용2", m1);
    Article a3 = makeTestArticle("제목3", "내용3", m2);

    articleRepository.save(a1);
    articleRepository.save(a2);
    articleRepository.save(a3);

    System.out.println("====== 테스트 데이터 등록 완료 ====== ");

  }

  private Article makeTestArticle(String title, String body, Member author) {
    return Article.builder()
        .title(title)
        .body(body)
        .author(author)
        .build();
  }

  private Member makeTestMemeber(String username, String password, String role) {
    return Member.builder()
        .username(username)
        .password(password)
        .role(role)
        .build();

  }
}

