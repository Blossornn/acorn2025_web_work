<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<p>
			오늘의 운세 : <strong>${fortuneToday}</strong>
			<a href="${pageContext.request.contextPath}/">돌아가기</a>
		</p>
	</div>
</body>
</html>