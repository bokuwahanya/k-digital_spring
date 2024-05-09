package com.rubypaper.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.rubypaper.dao.LogDAO;
import com.rubypaper.dao.MemberDAO;
import com.rubypaper.domain.LogVO;
import com.rubypaper.domain.MemberVO;

public class MemberService {
	MemberDAO memberDAO;
	LogDAO logDAO;
	
	public MemberService() {
		memberDAO =new MemberDAO();
		logDAO = new LogDAO();
	}
	
	public List<MemberVO> getAllMember(){
		Map<String, Object> map = null;
		
		try {
			map = memberDAO.getAllMember();
			logDAO.addLog("get", (String)map.get("sql"), (boolean)map.get("result")?1:0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (List<MemberVO>)map.get("list");
	}
	public MemberVO getMemberById(Integer id) {
		MemberVO m = null;
		try {
			m = memberDAO.getMemberId(id);
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
