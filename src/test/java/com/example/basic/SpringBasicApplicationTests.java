package com.example.basic;

import com.example.basic.domain.auth.dao.MemberDao;
import com.example.basic.domain.auth.entity.Member;
import com.example.basic.domain.auth.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class SpringBasicApplicationTests {

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private MemberRepository memberRepository;

	@Test
	@DisplayName("회원 수정 - save")
	void t7() {

		// 수정할 대상을 먼저 찾아오기

		Optional<Member> memberOpt = memberRepository.findById("kim");
		Member member = memberOpt.get();

		System.out.println(member.getUsername() + " : " + member.getRole());

		member.setRole("admin"); // 엔티티의 값을 바꾸고
		memberRepository.save(member); // 엔티티 다시 저장

	}

	@Test
	@DisplayName("회원 삭제 - delete, deleteById")
	void t6() {

		// id로 삭제
//		memberRepository.deleteById("kim");

		Optional<Member> memberOpt = memberRepository.findById("kim");
		Member member = memberOpt.get();

		// entity로 삭제
		memberRepository.delete(member);

	}

	@Test
	@DisplayName("회원 단건 조회 - findById")
	void t5() {
		Optional<Member> memberOpt = memberRepository.findById("kim");

		if (memberOpt.isPresent()) {
			Member member = memberOpt.get();

			System.out.println(member.getUsername());
			System.out.println(member.getPassword());
			System.out.println(member.getRole());

		}
	}

	@Test
	@DisplayName("회원 전체 조회 - findAll")
	void t4() {
		List<Member> memberList = memberRepository.findAll();

		for (Member member : memberList) {
			System.out.println(member.getUsername());
		}
	}

	@Test
	@DisplayName("회원 저장 - save")
	void t3() {
		Member member2 = Member.builder()
				.username("kim12")
				.password("qwer")
				.role("normal")
				.build();

		memberRepository.save(member2);
	}

	@Test
	void t2() {
		Member member2 = Member.builder()
				.username("kim")
				.password("qwer")
				.role("normal")
				.build();

		memberDao.save(member2);
	}

	@Test
	void t1() {

		for (int i = 1; i <= 10; i++) {
			System.out.println(i);
		}

		List<String> strList = new ArrayList<>();
		strList.add("aaa");
		strList.add("bbb");
		strList.add("ccc");

		for (int i = 0; i < strList.size(); i++) {
			System.out.println(strList.get(i));
		}

		for (String str : strList) {

		}

	}

}
