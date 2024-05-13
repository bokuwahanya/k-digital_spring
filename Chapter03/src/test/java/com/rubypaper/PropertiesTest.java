package com.rubypaper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest
public class PropertiesTest {

	
	@Autowired
	Environment enovironment;
	
	@Test
	public void testMethod() {
		System.out.println("테스트 합니다 다들 비켜주세요");
		System.out.println("이름" + enovironment.getProperty("author.name"));
		System.out.println("나이" + enovironment.getProperty("author.age"));
		System.out.println("국가" + enovironment.getProperty("author.nation"));
	}
}
