<%@page import="test.dto.BookDto"%>
<%@page import="test.dao.BookDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//출력해줄 책 목록 얻어오기
	List<BookDto> list=new BookDao().selectAll();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/book/list.jsp</title>
<style>
	body{
	padding:60px;
	}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/include/resource.jsp"></jsp:include>
<jsp:include page="/WEB-INF/include/navbar.jsp">
	<jsp:param value="book" name="thisPage"/>
</jsp:include>
	<div class="container">
		<a href="insertform.jsp">책 등록</a>
		<h1>책 목록</h1>
		<table border="1">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>저자</th>
					<th>출판사</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
			<%for(BookDto tmp:list){ %>
				<tr>
					<td><%=tmp.getNum() %></td>
					<td><%=tmp.getTitle() %></td>
					<td><%=tmp.getAuthor() %></td>
					<td><%=tmp.getPublisher() %></td>
					<td>
					<a href="updateform.jsp?num=<%=tmp.getNum() %>">수정</a>
					</td>
					<td>
					<a href="delete.jsp?num=<%=tmp.getNum() %>">삭제</a>
					</td>
				</tr>
			<%} %>
			</tbody>
		</table>
	</div>
	<jsp:include page="/WEB-INF/include/footer.jsp"></jsp:include>
</body>
</html>





