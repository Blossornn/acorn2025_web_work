package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import test.dto.UserDto;
import test.util.DbcpBean;

public class UserDao {

	private static UserDao dao;

	// static 초기화 블럭 (이 클래스가 최초로 사용될 때 한 번 실행되는 블럭)
	static {
		// static 초기화 작업을 여기서 한다.(UserDao 객체를 생성해서 static 필드에 담기)
		dao = new UserDao();
	}

	// 외부에서 USerDao 객체를 생성하지 못하도록 생성자를 private로 막는다.
	private UserDao() {
	}

	// UserDao 객체의 참조값을 리턴해주는 public static 메소드를 제공
	public static UserDao getInstance() {
		// static 필드에 저장된 dao의 참조값을 리턴 해 준다.
		return dao;
	}

	// 이메일과 프로필을 수정하는 메소드
	public boolean updateEmailProfile(UserDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 변화 된 row 의 갯수를 담을 변수 선언 하고 0 으로 초기화
		int rowCount = 0;

		try {
			conn = new DbcpBean().getConn();
			String sql = """
					UPDATE users
					SET email =?, profileImage =?, updatedAt = SYSDATE
					WHERE userName = ?
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 순서대로 필요한 값 바인딩
			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getProfileImage());
			pstmt.setString(3, dto.getUserName());
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

	// 이메일을 수정하는 메소드
	public boolean updateEmail(UserDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 변화 된 row 의 갯수를 담을 변수 선언 하고 0 으로 초기화
		int rowCount = 0;

		try {
			conn = new DbcpBean().getConn();
			String sql = """
					UPDATE users
					SET email = ? , updatedAt = SYSDATE
					WHERE userName = ?

					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 순서대로 필요한 값 바인딩
			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getUserName());
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

	// 비밀번호를 수정 반영하는 메소드
	public boolean updatePassword(UserDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 변화 된 row 의 갯수를 담을 변수 선언 하고 0 으로 초기화
		int rowCount = 0;

		try {
			conn = new DbcpBean().getConn();
			String sql = """
					UPDATE users
					SET password = ?, updatedAt = SYSDATE
					WHERE userName = ?

					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 순서대로 필요한 값 바인딩
			pstmt.setString(1, dto.getPassword());
			pstmt.setString(2, dto.getUserName());
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

	// userName을 이용해서 회원 한 명의 정보를 리턴하는 메소드
	public UserDto getByUserName(String userName) {
		UserDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			String sql = """
					SELECT num, password, email, profileImage, role, updatedAt, createdAt
					FROM users
					WHERE userName=?
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 값 바인딩
			pstmt.setString(1, userName);
			// select 문 실행하고 결과를 ResultSet으로 받아온
			rs = pstmt.executeQuery();
			// 반복문 돌면서 ResulteSet 에 담긴 데이터를 추출해서 리턴 해 줄 객체에 담는다
			// 만약 select 되는 row가 존재하다면
			if (rs.next()) {
				dto = new UserDto();
				dto.setNum(rs.getLong("num"));
				dto.setUserName(userName);
				dto.setPassword(rs.getString("password"));
				dto.setEmail(rs.getString("email"));
				dto.setProfileImage(rs.getString("profileImage"));
				dto.setRole(rs.getString("role"));
				dto.setUpdatedAt(rs.getString("updatedAt"));
				dto.setCreatedAt(rs.getString("createdAt"));

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

	// 회원정보 추가
	public boolean insert(UserDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 변화 된 row 의 갯수를 담을 변수 선언 하고 0 으로 초기화
		int rowCount = 0;

		try {
			conn = new DbcpBean().getConn();
			String sql = """
					INSERT INTO users
					(num, userName, password, email, updatedAt, createdAt)
					VALUES(users_seq.NEXTVAL, ?, ?, ?, SYSDATE, SYSDATE)
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 순서대로 필요한 값 바인딩
			pstmt.setString(1, dto.getUserName());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getEmail());

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
