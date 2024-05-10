package com.rubypaper.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rubypaper.dao.LogDAO;
import com.rubypaper.dao.MemberDAO;
import com.rubypaper.domain.LogVO;
import com.rubypaper.domain.MemberVO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	

	public List<MemberVO> getAllMember(){
		List<MemberVO> list = null;
		
		try {
			list = memberDAO.getAllMember();
			
			LogDAO logDAO = new LogDAO();
			logDAO.Log(LogVO.builder()
					.method("getAllMember")
					.sqlstring("select * from member ")
					.success(1)
					.build());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public MemberVO getMemberById(Integer id) {
		MemberVO m = null;
		try {
			m = memberDAO.getMemberId(id);
			LogDAO logDAO = new LogDAO();
			logDAO.Log(LogVO.builder()
					.method("getMemberById")
					.sqlstring("select * from member where id="+id)
					.success(1)
					.build());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}
	public MemberVO add(MemberVO memberVO) {
		MemberVO m = null;
		String x = memberVO.getName();
		String y = memberVO.getPass();
		String z = memberVO.getBirth();
		try {
			m = memberDAO.add(memberVO);
			LogDAO logDAO = new LogDAO();
			logDAO.Log(LogVO.builder()
					.method("POST")
					.sqlstring("insert into Member(username,password,birthyear) values("
							+x+","+y+","+z+")")
					.success(1)
					.build());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}
	
	public Integer update(MemberVO memberVO) throws SQLException {
		
		Integer m =null;
		String x = memberVO.getName();
		String y = memberVO.getPass();
		String z = memberVO.getBirth();
		try {
		m = memberDAO.update(memberVO);
		LogDAO logDAO = new LogDAO();
		logDAO.Log(LogVO.builder()
				.method("POST")
				.sqlstring("insert into Member(username,password,birthyear) values("
						+x+","+y+","+z+")")
				.success(1)
				.build());
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return m;
	}
	
	public Integer remove(Integer id) {
		Integer m = null;
		try {
			m = memberDAO.remove(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}
}
