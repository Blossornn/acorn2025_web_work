<%@page import="test.dao.MovieDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
// get 방식 파라미터로 전달되는 회원의 번호 얻어내기
int num = Integer.parseInt(request.getParameter("num"));
// 삭제 할 회원 정보를 삭제하고
MovieDao dao = new MovieDao();
dao.deleteByNum(num);
// 응답한다
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/movie/delete.jsp</title>
</head>
<body>
<script>
alert("삭제 완료!");
location.href="${pageContext.request.contextPath}/movie/list.jsp";
</script>

</body>
</html>