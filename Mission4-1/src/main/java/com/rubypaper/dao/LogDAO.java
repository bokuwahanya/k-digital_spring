package com.rubypaper.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.rubypaper.domain.LogVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class LogDAO {
	

	private final DataSource dataSource;

	public void addLog(String m, String q, int success) {
		LogVO logVO = LogVO.builder()
						.method(m)
						.sqlstring(q)
						.success(success)
						.build();
		PreparedStatement psmt = null;
		try {
			String query = "insert into DBLOG(METHOD,SQLSTRING,SUCCESS) values(?,?,?)";
			psmt = dataSource.getConnection().prepareStatement(query);
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
			try {
				if(psmt !=null) psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}