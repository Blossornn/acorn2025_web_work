<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
// GET 방식 파라미터 url 이라는 이름으로 전달되는 값이 있는 지 읽어 와 본다
String url = request.getParameter("url");
// 만일 넘어오는 값이 없다면
if (url == null) {
	String cPath = request.getContextPath();
	url = cPath + "/index.jsp";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/loginform.jsp</title>
</head>
<body>
	<div class="container">
		<h1>로그인 양식</h1>
		<form action="login.jsp" method="post">
		<%-- 로그인 성공 후에 이동 할 url 정보를 추가로 form 전송 되도록 한다 --%>
		<input type="hidden" name = "url" value= "<%=url %>" />
			<div>
				<label for="userName">아이디</label> 
				<input type="text" name="userName"
					id="userName" />
			</div>
			<div>
				<label for="password">비밀번호</label>
				 <input type="password"
					name="password" id="password" />
			</div>
			<div>
				<button type="submit">로그인</button>
		</form>
	</div>
</body>
</html>