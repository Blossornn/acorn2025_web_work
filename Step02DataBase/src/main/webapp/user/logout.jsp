<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	// 세션에 저장된 값을 삭제하면 로그아웃임
	// userName 이라는 키 값으로 저장된 값 삭제
/* 	session.removeAttribute("userName"); */
	

	// 세션영역에 저장된 정보 모두 삭제
	session.invalidate();


	// 응답하기
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/logout.jsp</title>
</head>
<body>
<script>
alert("로그아웃 되었습니다.");
location.href="${pageContext.request.contextPath}/";
</script>

</body>
</html>