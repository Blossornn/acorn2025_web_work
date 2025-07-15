<%@page import="test.dto.MovieDto"%>
<%@page import="test.dao.MovieDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    int num = Integer.parseInt(request.getParameter("num"));
    	MovieDto dto =  new MovieDao().getByNum(num);
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/movie/updateform.jsp</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr"
	crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
</head>
<body>
<h1>영화 제목 수정하기</h1>
<form action="${pageContext.request.contextPath}/movie/update.jsp" method="post">
<table>
  <tr>
    <td><label for="num">번호 :</label></td>
    <td><input type="text" name="num" id="num" value="<%=dto.getNum() %>" readonly /></td>
  </tr>
  <tr>
    <td><label for="title">제목 :</label></td>
    <td><input type="text" name="title" id="title" value="<%=dto.getTitle() %>" /></td>
  </tr>
  <tr>
    <td><label for="publisher">감독 명 :</label></td>
    <td><input type="text" name="publisher" id="publisher" value="<%=dto.getPublisher() %>" /></td>
  </tr>
  <tr>
    <td colspan="2" style="text-align:center;">
      <button type="submit">수정하기</button>
      <button type="reset">취소</button>
    </td>
  </tr>
</table>
</form>

</body>
</html>