package com.example.basic;

import com.example.basic.domain.auth.dao.MemberDao;
import com.example.basic.domain.auth.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SpringBasicApplicationTests {

	@Autowired
	private MemberDao memberDao;

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
