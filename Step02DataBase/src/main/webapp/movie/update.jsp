<%@page import="test.dao.MovieDao"%>
<%@page import="test.dto.MovieDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
int num = Integer.parseInt(request.getParameter("num"));
String title = request.getParameter("title");
String publisher = request.getParameter("publisher");

MovieDto dto = new MovieDto();
dto.setNum(num);
dto.setTitle(title);
dto.setPublisher(publisher);
boolean isSuccess = new MovieDao().update(dto);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/movie/update.jsp</title>
</head>
<body>
<%if(isSuccess){ %>
<p>
<strong><%=publisher %></strong> 감독님의 영화 제목이 수정되었습니다.
<a href="list.jsp">확인</a>
</p>
<%}else{ %>
<p>
수정실패
<a href="updateform.jsp?num<%=num%>">다시 수정하러 가기</a>
</p>
<% }%>
</body>
</html>