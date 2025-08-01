package test.dao;
/*
 *  Data Access Object 객체를 생성할 클래스 정의
 *  
 *  - DB에 insert, update, delete, select 작업을 하는 코드
 *  
 *  
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.dto.MemberDto;
import test.dto.MovieDto;
import test.util.DbcpBean;

public class MovieDao {
	// 회원 번호를 이용해서 회원 1 명의 정보를 리턴 해 주는 메소드
	public MovieDto getByNum(int num) {
		MovieDto dto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			String sql = """
							SELECT  title, publisher
							FROM Movie
							WHERE num = ?
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 값 바인딩
			pstmt.setInt(1, num);
			// select 문 실행하고 결과를 ResultSet으로 받아온
			rs = pstmt.executeQuery();
			// 반복문 돌면서 ResulteSet 에 담긴 데이터를 추출해서 리턴 해 줄 객체에 담는다
			while (rs.next()) {
				dto = new MovieDto();
				dto.setNum(num);
				dto.setTitle(rs.getString("title"));
				dto.setPublisher(rs.getString("publisher"));
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return dto;
	}

	// 회원 전체 목록을 SELECT해서 List에 담아서 리턴하는 메소드
	public List<MovieDto> selectAll() {
		List<MovieDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			String sql = """
							SELECT num, title, publisher
							FROM Movie
							ORDER BY num ASC
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 값 바인딩

			// select 문 실행하고 결과를 ResultSet으로 받아온
			rs = pstmt.executeQuery();
			// 반복문 돌면서 ResulteSet 에 담긴 데이터를 추출해서 리턴 해 줄 객체에 담는다
			while (rs.next()) {
				MovieDto dto = new MovieDto();
				dto.setNum(rs.getInt("num"));
				dto.setTitle(rs.getString("title"));
				dto.setPublisher(rs.getString("publisher"));
				// 회원 한 명의 정보가 담긴 새로운 MemberDto 객체의 참조값을 List에 누적시키기
				list.add(dto);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return list;
	}

	// 회원 한 명의 정보를 DB에서 수정하고 성공 여부를 리턴하는 메소드
	public boolean update(MovieDto dto) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		// 변화 된 row 의 갯수를 담을 변수 선언 하고 0 으로 초기화
		int rowCount = 0;

		try {
			conn = new DbcpBean().getConn();
			String sql = """
							UPDATE Movie
							SET title = ?, publisher = ?
							WHERE num = ?
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 순서대로 필요한 값 바인딩
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getPublisher());
			pstmt.setInt(3, dto.getNum());
			// sql 문 실행하고 변화된 (추가된, 수정된, 삭제된) row의 갯수 리턴 받기
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {

			}
		}
		// 변화 된 rowCount 값을 조사해서 작업의 성공 여부를 알아낼 수 있다.
		if (rowCount > 0) {
			return true; // 작업 성공이라는 의미에서 true
		} else {
			return false; // 작업 실패라는 의미에서 false
		}
	}

	// 회원 한 명의 정보를 DB에서 삭제하고 성공 여부를 리턴 하는 메소드
	public boolean deleteByNum(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 변화 된 row 의 갯수를 담을 변수 선언 하고 0 으로 초기화
		int rowCount = 0;

		try {
			conn = new DbcpBean().getConn();
			String sql = """
							DELETE FROM Movie
							WHERE num = ?
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 순서대로 필요한 값 바인딩
			pstmt.setInt(1, num);
			// sql 문 실행하고 변화된 (추가된, 수정된, 삭제된) row의 갯수 리턴 받기
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {

			}
		}
		// 변화 된 rowCount 값을 조사해서 작업의 성공 여부를 알아낼 수 있다.
		if (rowCount > 0) {
			return true; // 작업 성공이라는 의미에서 true
		} else {
			return false; // 작업 실패라는 의미에서 false
		}
	}

	public boolean insert(MovieDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 변화 된 row 의 갯수를 담을 변수 선언 하고 0 으로 초기화
		int rowCount = 0;

		try {
			conn = new DbcpBean().getConn();
			String sql = """
							INSERT INTO Movie
							(num, title, publisher)
							VALUES(Movie_SEQ.NEXTVAL, ?, ?)
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 순서대로 필요한 값 바인딩
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getPublisher());
			// sql 문 실행하고 변화된 (추가된, 수정된, 삭제된) row의 갯수 리턴 받기
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {

			}
		}
		// 변화 된 rowCount 값을 조사해서 작업의 성공 여부를 알아낼 수 있다.
		if (rowCount > 0) {
			return true; // 작업 성공이라는 의미에서 true
		} else {
			return false; // 작업 실패라는 의미에서 false
		}
	}
}
