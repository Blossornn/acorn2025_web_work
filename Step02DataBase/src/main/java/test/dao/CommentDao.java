package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.dto.CommentDto;
import test.util.DbcpBean;

public class CommentDao {

	private static CommentDao dao;
	static {
		dao = new CommentDao();
	}
	// 생성자를 private로 해서 외부에서 객체를 생성하지 못하도록
	private CommentDao() {}
	// 자신의 참조값을 리턴해주는 static 메소드 제공
	public static CommentDao getInstance() {
		return dao;
	}
	
	// 댓글을 삭제하는 메소드(deleted => yes)
	public boolean delete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 변화 된 row 의 갯수를 담을 변수 선언 하고 0 으로 초기화
		int rowCount = 0;

		try {
			conn = new DbcpBean().getConn();
			String sql = """
					UPDATE comments
					SET deleted = 'yes'
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
	
	// 댓글을 수정하는 메소드
	public boolean update(CommentDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 변화 된 row 의 갯수를 담을 변수 선언 하고 0 으로 초기화
		int rowCount = 0;

		try {
			conn = new DbcpBean().getConn();
			String sql = """
					UPDATE comments
					SET content = ?
					WHERE num = ?
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 순서대로 필요한 값 바인딩
			pstmt.setString(1, dto.getContent());
			pstmt.setInt(2, dto.getNum());

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
	
	// 원글(parentNum)에 달린 모든 댓글을 리턴 하는 메소드
	public List<CommentDto> selectList(int parentNum){
		List<CommentDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			String sql = """
				SELECT comments.num, comments.writer, comments.targetWriter, comments.content, comments.deleted, comments.groupNum,
				 comments.createdAt, users.profileImage
				FROM comments
				INNER JOIN users ON comments.writer = users.userName
				WHERE parentNum = ?
				ORDER BY comments.groupNum ASC, comments.num ASC
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 값 바인딩
			pstmt.setInt(1, parentNum);
			// select 문 실행하고 결과를 ResultSet으로 받아온
			rs = pstmt.executeQuery();
			// 반복문 돌면서 ResulteSet 에 담긴 데이터를 추출해서 리턴 해 줄 객체에 담는다
			while (rs.next()) {
				CommentDto dto = new CommentDto();
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setTargetWriter(rs.getString("targetWriter"));
				dto.setContent(rs.getString("content"));
				dto.setParentNum(parentNum);
				dto.setGroupNum(rs.getInt("groupNum"));
				dto.setDeleted(rs.getString("deleted"));
				dto.setCreatedAt(rs.getString("createdAt"));
				dto.setProfileImage(rs.getString("profileImage"));
				
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
	
	// 댓글 정보를 DB에 저장하는 메소드
	public boolean insert(CommentDto dto) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 변화 된 row 의 갯수를 담을 변수 선언 하고 0 으로 초기화
		int rowCount = 0;

		try {
			conn = new DbcpBean().getConn();
			String sql = """
					INSERT INTO comments
					(num, writer, targetWriter, content, parentNum, groupNum)
					VALUES(?,?,?,?,?,?)
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 순서대로 필요한 값 바인딩
			pstmt.setInt(1, dto.getNum());
			pstmt.setString(2, dto.getWriter());
			pstmt.setString(3, dto.getTargetWriter());
			pstmt.setString(4, dto.getContent());
			pstmt.setInt(5, dto.getParentNum());
			pstmt.setInt(6, dto.getGroupNum());
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
	
	// 저장할 댓글의 글 번호를 리턴해주는 메소드
	public int getSequence() {
		int num = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			String sql = """
					SELECT comments_seq.NEXTVAL AS num FROM DUAL
					""";
			pstmt = conn.prepareStatement(sql);
			// ? 에 값 바인딩
			
			// select 문 실행하고 결과를 ResultSet으로 받아온
			rs = pstmt.executeQuery();
			// 반복문 돌면서 ResulteSet 에 담긴 데이터를 추출해서 리턴 해 줄 객체에 담는다
			if (rs.next()) {
				num = rs.getInt("num");
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
		return num;
	}
}
