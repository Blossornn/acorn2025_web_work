<%@page import="test.dao.CommentDao"%>
<%@page import="test.dto.CommentDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
// 폼 전송되는 수정 할 댓글의 정보를 얻어오기
int num = Integer.parseInt(request.getParameter("num"));
String content = request.getParameter("content");

// 원글의 번호
String parentNum = request.getParameter("parentNum");

CommentDto dto = new CommentDto();
dto.setNum(num);
dto.setContent(content);

boolean isSuccess = CommentDao.getInstance().update(dto);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/board/comment-update.jsp</title>
</head>
<body>
	<script>
		
	<%if (isSuccess) {%>
		alert("수정 했습니다.");
		location.href="${pageContext.request.contextPath}/board/view.jsp?num=<%=parentNum%>";
	<%} else {%>
		alert("수정 실패");
		location.href="${pageContext.request.contextPath}/board/view.jsp?num=<%=parentNum%>";
	<%}%>
		
	</script>
</body>
</html>