package com.rubypaper.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rubypaper.domain.MemberVO;

public class MemberDAO {
	public Connection con;
	
	public MemberDAO() {
		try {
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/sqlprg", "sa", "1234");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, Object> getAllMember() throws SQLException{
		List<MemberVO> list = new ArrayList<>();
		ResultSet rs = null;
		Statement stmt = null;
		boolean flag = true;
		
		String query = "select * from member ";
		try {
			stmt = con.createStatement(); // 질의객체 생성
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
			if(rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("sql", query);
		map.put("result", flag);
		
		return map;		
	}
	
	public MemberVO getMemberId(Integer id) throws SQLException {
		MemberVO memberVO = null;
		ResultSet rs = null;
		PreparedStatement psmt = null;
		String query = "select * from member where id=?";
		try {
			psmt = con.prepareStatement(query);
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
			if(rs != null) {
				rs.close();
			}
			if(psmt != null) {
				psmt.close();
			}
		}
		return memberVO;
	}
	
	public MemberVO add(MemberVO memberVO) throws SQLException {
		PreparedStatement psmt = null;
		int memberId = memberVO.getId();
		if(getMemberId(memberId)!= null) {
			return null;
		}
		try {
			String query = "insert into Member(username,password,birthyear) values(?,?,?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, memberVO.getName());
			psmt.setString(2, memberVO.getPass());
			psmt.setString(3, memberVO.getBirth());
			int result = psmt.executeUpdate();
			if(result == 0) {
				return null;
			}
		}catch(SQLException e) {
			System.out.println("멤버 값 입력 중 예외발생");
			e.printStackTrace();
		}finally {
			if(psmt != null) {
				psmt.close();
			}
		}
		return memberVO;
	}
	
	public Integer update(MemberVO memberVO) throws SQLException {
		Statement st =con.createStatement();
		PreparedStatement psmt = null;
		int memberId = memberVO.getId();
		if(getMemberId(memberId)==null) {
			return 0;
		}
		try {  
			String str = "";
			String x = memberVO.getName();
			String y = memberVO.getPass();
			String z = memberVO.getBirth();
			if (x != null && !x.isEmpty()) { 
				str = "username='" + x +"' ";
			}
			if(y != null && !y.isEmpty()) {
				if(!str.isEmpty()) str += ","; // 콤마 때문에
				str = str + "password='" + y + "' ";
			}
			if(z != null && !z.isEmpty()) {
				if(!str.isEmpty()) str += ",";
				str = str + "birthyear='" + z + "' ";
			}
			String sql = "update member set " +str + " where id=" + memberId;
			st.executeUpdate(sql);
			System.out.println("member 테이블에 [id: " + memberId + "]가 수정되었습니다.");
		}
		catch(SQLException e) {
			System.out.println("멤버 값 입력 중 예외발생");
			e.printStackTrace();
		}finally {
			if(psmt != null) {
				psmt.close();
			}
		}
		return 1;
	}
	public Integer remove(Integer id) throws SQLException{
		PreparedStatement psmt = null;
		
		if(getMemberId(id) == null){
			return 0;
		}
		String query ="delete from member where id=?";
				try {
					psmt = con.prepareStatement(query);
					psmt.setInt(1, id);
					int result = psmt.executeUpdate();
					System.out.println(result);
				}catch(Exception e) {
					System.out.println("멤버 삭제 중 예외발생");
					e.printStackTrace();
					return 0;
				}finally {
					if(psmt != null) {
						psmt.close();
					}
				}
				return 1;
	}
	
}
