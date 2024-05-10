package com.rubypaper.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rubypaper.domain.MemberVO;

@Repository
public class MemberDAO {
	public Connection con;
	public Statement stmt;
	public ResultSet rs;
	public PreparedStatement psmt;

	public MemberDAO() {
		try {
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/sqlprg", "sa", "1234");
			stmt = con.createStatement(); //쿼리문 생성
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("멤버 다오 생성");
	}
	
	public List<MemberVO> getAllMember(){
		List<MemberVO> list = new ArrayList<>();
		
		String query = "select * from member ";
		
		try {
			rs = stmt.executeQuery(query);
			
			//반환된 게물 목록을 List 컬렉션에 추가
			while(rs.next()) {
				list.add(MemberVO.builder()
						.id(rs.getInt(1))
						.name(rs.getString(2))
						.pass(rs.getString(3))
						.birth(rs.getString(4))
						.regidate(rs.getDate(5))
						.build());
			}
		}catch(Exception e) {
			System.out.println("멤버 조회 중 예외 발생");
			e.printStackTrace();
		}
		
		return list;
	}
	public MemberVO getMemberById(Integer id) {
		MemberVO memberVO = null;
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
		} catch (SQLException e) {
			System.out.println("멤버를 아이디값으로 조회중 예외발생");
		}
		return memberVO;
	}
	
	public MemberVO add(MemberVO memberVO) {
		int memberId = memberVO.getId();
		if(getMemberById(memberId)!= null) {
		return null;
	}
		try {
			String query ="insert into Member(username,password,birthyear) values(?,?,?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, memberVO.getName());
			psmt.setString(2, memberVO.getPass());
			psmt.setString(3, memberVO.getBirth());
			int result = psmt.executeUpdate();
			System.out.println(result);
			if(result == 0) {
				return null;
			}
		} catch(SQLException e) {
			System.out.println("멤버에 값 입력 중 예외발생");
		}
		return memberVO;
	}
	
	public Integer update(MemberVO memberVO) throws SQLException {
		Statement st =con.createStatement();
		int memberId = memberVO.getId();
		if(getMemberById(memberId)== null) {
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
		}finally {
			if(psmt != null) {
				psmt.close();
			}
		}
		return 1;
		
	}
	
	public Integer remove(Integer id) 
	{
		if(getMemberById(id)== null) {
		return 0;
	}
		String query = "delete from member where id=? ";
		
		try {
			System.out.println("리무브 오류");
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			int result = psmt.executeUpdate();
			System.out.println(result);
		}catch(Exception e) {
			 e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	//연결 해제
	public void close() {
		try {
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
			if(con != null)
				con.close();
			
			System.out.println("JDBC 자원 해제");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
