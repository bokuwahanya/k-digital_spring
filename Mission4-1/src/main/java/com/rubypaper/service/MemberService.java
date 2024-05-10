package com.rubypaper.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rubypaper.dao.LogDAO;
import com.rubypaper.dao.MemberDAO;
import com.rubypaper.domain.LogVO;
import com.rubypaper.domain.MemberVO;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
//필드가 많아지면 이게 더 낫다
@Service
public class MemberService {
	
	
	private	final MemberDAO memberDAO;
	private final LogDAO logDAO;
	
	Map<String, Object> map = null;
	
	@SuppressWarnings("unchecked")
	public List<MemberVO> getAllMember(){
		
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
			map = memberDAO.getMemberById(id);
			m = (MemberVO) map.get("byId");
			logDAO.addLog("get",(String)map.get("sql") , (boolean)map.get("result")?1:0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}
	public MemberVO add(MemberVO memberVO) {
		MemberVO m = null;
		try {
			map = memberDAO.add(memberVO);
			m = (MemberVO) map.get("add");
			logDAO.addLog("post",(String)map.get("sql"), (boolean)map.get("result")?1:0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}
	

	public int update(MemberVO memberVO) throws SQLException {
		
		Integer m =null;
		try {
		map = memberDAO.update(memberVO);
		m = (int) map.get("update");
		logDAO.addLog("put",(String)map.get("sql"), (boolean)map.get("result")?1:0);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return m;
	}
	
	public Integer remove(Integer id) {
		Integer m = null;
		try {
			map = memberDAO.remove(id);
			m = (Integer) map.get("delete");
			logDAO.addLog("delete",(String)map.get("sql"), (boolean)map.get("result")?1:0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}
}
