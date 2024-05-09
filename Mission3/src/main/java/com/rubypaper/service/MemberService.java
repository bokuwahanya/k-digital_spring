package com.rubypaper.service;

import java.sql.SQLException;
import java.util.List;

import com.rubypaper.dao.MemberDAO;
import com.rubypaper.domain.MemberVO;


// 생성자 선언을 필드에
public class MemberService {
	MemberDAO memberDAO;
	
	public MemberService() {
		memberDAO = new MemberDAO();
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