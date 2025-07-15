<%@page import="test.dao.BookDao"%>
<%@page import="test.dto.BookDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
int num = Integer.parseInt(request.getParameter("num"));
String title = request.getParameter("title");
String author = request.getParameter("author");
String publisher = request.getParameter("publisher");

BookDto dto = new BookDto();
dto.setNum(num);
dto.setTitle(title);
dto.setAuthor(author);
dto.setPublisher(publisher);
boolean isSuccess = new BookDao().update(dto);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	if (isSuccess) {
	%>
	<script>
	alert("<%=title%> 책의 정보를 수정 했습니다.");
	location.href="list.jsp";
	<%} else {%>
	alert("수정 실패");
	location.href="updateform.jsp?num=<%=num%>";
	<%}%>
	</script>
</body>
</html>