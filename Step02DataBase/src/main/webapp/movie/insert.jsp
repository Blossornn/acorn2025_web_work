<%@page import="test.dao.MovieDao"%>
<%@page import="test.dto.MovieDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% 
    String title = request.getParameter("title");
    String publisher = request.getParameter("publisher");
    MovieDto dto = new MovieDto();
    dto.setTitle(title);
    dto.setPublisher(publisher);
    MovieDao dao = new MovieDao();
    boolean isSuccess = dao.insert(dto);
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>movie/insert.jsp</title>
</head>
<body>
<%if(isSuccess) {%>
<p>
<strong><%=publisher %></strong> 감독님의 영화가 추가 되었습니다.
<a href="${pageContext.request.contextPath}/movie/list.jsp">다른 작품 보러가기</a>
</p>
<%}else{ %>
	<p>영화 추가 실패</p>
	<a href="${pageContext.request.contextPath}/movie/insertform.jsp">다시
		입력하기</a>
	
<%} %>
</body>
</html>