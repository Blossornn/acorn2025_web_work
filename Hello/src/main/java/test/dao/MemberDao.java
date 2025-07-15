package test.dao;

/*
 *  Data Access Object 객체를 생성할 클래스 정의
 *  - DB에 insert, update, delete, select 작업을 하는 코드
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.dto.MemberDto;
import test.util.DBConnector;

public class MemberDao {

	// 회원 번호를 이용해서 회원 1명의 정보를 리턴
	public MemberDto getByNum(int num) {
		MemberDto dto = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = new DBConnector().getConn();
			String sql = """
							SELECT name, addr
							FROM member
							WHERE num = ?
						""";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new MemberDto();
				dto.setNum(num);
				dto.setName(rs.getString("name"));
				dto.setAddr(rs.getString("addr"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if (rs != null) rs.close(); } catch (Exception e) {}
			try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
			try { if (conn != null) conn.close(); } catch (Exception e) {}
		}
		return dto;
	}

	// 전체 회원 조회
	public List<MemberDto> selectAll() {
		List<MemberDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = new DBConnector().getConn();
			String sql = "SELECT num, name, addr FROM member ORDER BY num ASC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberDto dto = new MemberDto();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setAddr(rs.getString("addr"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if (rs != null) rs.close(); } catch (Exception e) {}
			try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
			try { if (conn != null) conn.close(); } catch (Exception e) {}
		}
		return list;
	}

	// 회원 정보 수정
	public boolean update(MemberDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;

		try {
			conn = new DBConnector().getConn();
			String sql = "UPDATE member SET name = ?, addr = ? WHERE num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddr());
			pstmt.setInt(3, dto.getNum());
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
			try { if (conn != null) conn.close(); } catch (Exception e) {}
		}
		return rowCount > 0;
	}

	// 회원 삭제
	public boolean deleteByNum(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;

		try {
			conn = new DBConnector().getConn();
			String sql = "DELETE FROM member WHERE num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
			try { if (conn != null) conn.close(); } catch (Exception e) {}
		}
		return rowCount > 0;
	}

	// 회원 추가
	public boolean insert(MemberDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;

		try {
			conn = new DBConnector().getConn();
			String sql = "INSERT INTO member (num, name, addr) VALUES (member_seq.NEXTVAL, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddr());
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
			try { if (conn != null) conn.close(); } catch (Exception e) {}
		}
		return rowCount > 0;
	}
}
