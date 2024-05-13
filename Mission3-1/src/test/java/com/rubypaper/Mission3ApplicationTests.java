package com.rubypaper;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.dao.MemberDAO;
import com.rubypaper.domain.MemberVO;

@SpringBootTest
class Mission3ApplicationTests {

	@Autowired
	private MemberDAO memberDAO;
	@Test
	public void test() {
		List<MemberVO> list = memberDAO.getAllMember();
		
		for(MemberVO m : list)
			System.out.println(m);
	}

}
