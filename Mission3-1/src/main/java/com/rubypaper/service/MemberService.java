 package com.rubypaper.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rubypaper.dao.MemberDAO;
import com.rubypaper.domain.MemberVO;


// 생성자 선언을 필드에
@Service
public class MemberService {
	
	@Autowired
	MemberDAO memberDAO;
	
//	public MemberService(MemberDAO memberDAO) {
//		this.memberDAO = memberDAO;
//	}
	
//	public void setMemberDao(MemberDAO memberDAO) {
//		this.memberDAO = memberDAO;
//	}
	
	public MemberService() {
//		memberDAO = new MemberDAO();
		System.out.println("멤버 서비스 생성");
	}
	
	public List<MemberVO> getAllMember(){
		List<MemberVO> list = memberDAO.getAllMember();
		return list;
	}
	
	public MemberVO getMemberById(Integer id) {
		MemberVO m = memberDAO.getMemberById(id);
		return m;
	}
	public MemberVO add(MemberVO memberVO) {
		MemberVO m = memberDAO.add(memberVO);
		return m;
	}
	
	public Integer update(MemberVO memberVO) throws SQLException {
		Integer m = memberDAO.update(memberVO);
		return m;
	}
	
	public Integer remove(Integer id) {
		Integer m = memberDAO.remove(id);
		return m;
	}
}