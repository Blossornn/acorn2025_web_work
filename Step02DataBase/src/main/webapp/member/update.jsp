<%@page import="test.dao.MemberDao"%>
<%@page import="test.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
int num = Integer.parseInt(request.getParameter("num"));
String name = request.getParameter("name");
String addr = request.getParameter("addr");

MemberDto dto = new MemberDto();
dto.setNum(num);
dto.setName(name);
dto.setAddr(addr);
boolean isSuccess = new MemberDao().update(dto);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/update.jsp</title>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" 
	integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
</head>
<body>
	<div class="container mt-5">
		<% if (isSuccess) { %>
			<div class="alert alert-success" role="alert">
				<strong><%=num%></strong> 번 회원의 정보를 성공적으로 수정했습니다.
			</div>
			<a href="list.jsp" class="btn btn-primary"><i class="bi bi-list"></i> 목록 보기</a>
		<% } else { %>
			<div class="alert alert-danger" role="alert">
				회원 정보 수정에 실패했습니다.
			</div>
			<a href="updateform.jsp?num=<%=num%>" class="btn btn-danger"><i class="bi bi-arrow-counterclockwise"></i> 다시 시도</a>
		<% } %>
		
	</div>

	<!-- Bootstrap Icons (선택) -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
</body>
</html>
