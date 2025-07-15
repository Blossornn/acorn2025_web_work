<%@page import="test.dao.MemberDao"%>
<%@page import="test.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String name = request.getParameter("name");
String addr = request.getParameter("addr");

MemberDto dto = new MemberDto();
dto.setName(name);
dto.setAddr(addr);
MemberDao dao = new MemberDao();
boolean isSuccess = dao.insert(dto);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/insert.jsp</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr"
	crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
</head>
<body>
<div class="container" >
	<%
	if (isSuccess) {
	%>
	<p class = "alert alert-success mt-5 ">
	<i class="bi bi-check-circle-fill"></i>
		<strong><%=name%></strong> 님의 정보를 성공적으로 저장했습니다.<br /> 
		<a class ="alert-link" href="${pageContext.request.contextPath}/member/list.jsp">
		확인</a>
	</p>
	<%
	} else {
	%>
	<p class = "alert alert-danger mt-5 ">
	<i class="bi bi-exclamation-diamond-fill"></i>회원 정보저장 실패 <br />
	<a class ="alert-link" href="${pageContext.request.contextPath}/member/insertform.jsp">다시
		입력하기</a>
		</p>
	<%
	} 
	%>
	
	</div>
</body>
</html>