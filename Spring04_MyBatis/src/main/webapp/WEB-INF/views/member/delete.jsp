<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/WEB-INF/views/member/delete.jsp</title>
</head>
<body>
	<%-- 파라미터로 전달 되었던 값을 필요하다면 EL로 추출해서 응답할 수 있음 --%>
	<script>
		alert("${param.num} 번 회원 정보 삭제 완료");
		location.href = "${pageContext.request.contextPath}/member/list"
	</script>
</body>
</html>