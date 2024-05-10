package com.rubypaper.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.rubypaper.domain.LogVO;

public class LogDAO {
	public Connection con;

	public LogDAO() {
		try {
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/sqlprg", "sa", "1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Log(LogVO logVO) {
		PreparedStatement psmt = null;
		try {
			String query = "insert into DBLOG(METHOD,SQLSTRING,SUCCESS) values(?,?,?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, logVO.getMethod());
			psmt.setString(2, logVO.getSqlstring());
			psmt.setInt(3, logVO.getSuccess());
			int result = psmt.executeUpdate();
			if(result ==0) {
			}
		}catch(SQLException e) {
			System.out.println("로그 값 입력 중 예외발생");
			e.printStackTrace();
		}finally {
			if(psmt !=null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}