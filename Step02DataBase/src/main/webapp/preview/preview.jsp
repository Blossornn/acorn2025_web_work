<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String name = request.getParameter("name");
String gender = request.getParameter("gender");
String intro = request.getParameter("intro");


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Preview</title>
</head>
<body>
	<div class="container">
	<h3>정보 확인</h3>
	<p>이름 :<%=request.getParameter("name") %></p>
	<p>성별 :<%=request.getParameter("gender") %></p>
	<p>자기소개 :<%=request.getParameter("intro") %></p>

	</div>
</body>
</html>