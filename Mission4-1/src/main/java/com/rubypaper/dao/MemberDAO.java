package com.rubypaper.dao;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rubypaper.domain.MemberVO;

import lombok.RequiredArgsConstructor;

@Repository
public class MemberDAO {

	
	@Autowired
	private  DataSource dataSource;
	//spring starter에 spring boot data 추가. 
	//resources//apllication.properties 에만들면된다


	public Map<String, Object> getAllMember() throws SQLException{
		List<MemberVO> list = new ArrayList<>();
		boolean flag = true;
		ResultSet rs = null;
		Statement stmt = null;
		
		String query = "select * from member ";
		
		try {
			stmt = dataSource.getConnection().createStatement(); // 질의객체 생성
			rs = stmt.executeQuery(query); // 질의 및 결과 셋 받기

			while(rs.next()) { // 결과 셋 순회 커서프로세싱
				list.add(MemberVO.builder()
						.id(rs.getInt(1))
						.name(rs.getString(2))
						.pass(rs.getString(3))
						.birth(rs.getString(4))
						.regidate(rs.getDate(5))
						.build());
			}
		}catch(Exception e) {
			System.out.println("게시물 조회중 오류");
			e.printStackTrace();
			flag = false;
		}finally {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
		}

		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("sql", query);
		map.put("result", flag);

		return map;		
	}

	public Map<String,Object> getMemberById(Integer id) throws SQLException {
		MemberVO memberVO = null;
		boolean flag = true;
		ResultSet rs = null;
		PreparedStatement psmt = null;
		
		String query = "select * from member where id=?";
		
		try {
			psmt = dataSource.getConnection().prepareStatement(query);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();

			if(rs.next()) {
				memberVO = MemberVO.builder()
						.id(rs.getInt(1))
						.name(rs.getString(2))
						.pass(rs.getString(3))
						.birth(rs.getString(4))
						.regidate(rs.getDate(5))
						.build();

			}
		}catch(SQLException e) {
			System.out.println("멤버를 아이디값으로 조회중 예외발생");
			e.printStackTrace();
		}finally {
			query = psmt.toString();
			int n = query.indexOf(':');
			query = query.substring(n+2);
			if(rs != null) rs.close();
			if(psmt != null)psmt.close();
			
		}
		Map<String,Object>map = new HashMap<>();
		map.put("byId", memberVO);
		map.put("sql",query);
		map.put("result", flag);

		return map;
	}

	public Map<String,Object> add(MemberVO memberVO) throws SQLException {
		PreparedStatement psmt = null;
		boolean flag = true;
		
		String query = "insert into Member(username,password,birthyear) values(?,?,?)";
		
		try {
			psmt = dataSource.getConnection().prepareStatement(query);
			psmt.setString(1, memberVO.getName());
			psmt.setString(2, memberVO.getPass());
			psmt.setString(3, memberVO.getBirth());
			int result = psmt.executeUpdate();
			System.out.println(result);
			if(result == 0) {
				return null;
			}
		}catch(SQLException e) {
			System.out.println("멤버 값 입력 중 예외발생");
			e.printStackTrace();
		}finally {
			query = psmt.toString();
			int n = query.indexOf(':');
			query = query.substring(n+2);
			if(psmt != null) psmt.close();
		}
		Map<String,Object>map = new HashMap<>();
		map.put("add", memberVO);
		map.put("sql",query);
		map.put("result", flag);

		return map;
	}

	public Map<String,Object> update(MemberVO memberVO) throws SQLException {
		PreparedStatement psmt = null;
		int memberId = memberVO.getId();
		if(getMemberById(memberId)==null) {
			return null;
		}
		boolean flag = true;
		int result ;
		
		String query = "UPDATE Member"
				+ " SET"
				+ " username = COALESCE(?, username),"
				+ " password = COALESCE(?, password),"
				+ " birthyear = COALESCE(?, birthyear)"
				+ " WHERE id = ?";
	
		try { 
			psmt =dataSource.getConnection().prepareStatement(query);
			psmt.setString(1, memberVO.getName());
			psmt.setString(2, memberVO.getPass());
			psmt.setString(3, memberVO.getBirth());
			psmt.setInt(4, memberVO.getId());

			result = psmt.executeUpdate();
			System.out.println("member 테이블에 [id: " + memberId + "]가 수정되었습니다.");
		}
		catch(SQLException e) {
			System.out.println("멤버 값 입력 중 예외발생");
			e.printStackTrace();
			return null;
		}finally {
			query = psmt.toString();
			int n = query.indexOf(':');
			query = query.substring(n+2);
			System.out.println("query:" + query);
			if(psmt != null) psmt.close();
		}
		Map<String,Object>map = new HashMap<>();
		map.put("update", result);
		map.put("sql",query);
		map.put("result", flag);

		return map;
	}
	public  Map<String,Object> remove(Integer id) throws SQLException{
		PreparedStatement psmt = null;

		if(getMemberById(id) == null){
			return null;
		}
		boolean flag = true;
		int result;
		
		String query ="delete from member where id=?";
		
		try {
			psmt = dataSource.getConnection().prepareStatement(query);
			psmt.setInt(1, id);
			result = psmt.executeUpdate();
			System.out.println("member 테이블에 [id: " + id + "]가 삭제되었습니다.");
		}catch(Exception e) {
			System.out.println("멤버 삭제 중 예외발생");
			e.printStackTrace();
			return null;
		}finally {
			query = psmt.toString();
			int n = query.indexOf(':');
			query = query.substring(n+2);
			if(psmt != null) {
				psmt.close();
			}
		}
		Map<String,Object>map = new HashMap<>();
		map.put("delete", result);
		map.put("sql",query);
		map.put("result", flag);

		return map;
	}

}
