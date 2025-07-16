<%@page import="test.dao.UserDao"%>
<%@page import="test.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String userName = (String)session.getAttribute("userName");
    UserDto dto = new UserDao().getByUserName(userName);
    %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/edit.jsp</title>
</head>
<body>
	<div class="container">
		<h1>가입정보 수정 양식</h1>
		<%--input type = "file" 이 있는 form 전송 방식은 다르다.
		따라서 enctype ="multipart/form-data" 속성을 form에 추가해준다
		서버에서 해당 요청을 처리하는 방법도 다르기 때문에 jsp가 아닌 서블릿에서 처리를 하자 --%>
		<form action="${pageContext.request.contextPath }/user/update" 
		method="post" enctype = "multipart/form-data">
			<div>
				<label for="userName">아이디</label>
				<input type="text" name="userName" value="<%=dto.getUserName() %>" readonly/>
			</div>
			<div>
				<label for="email">이메일</label>
				<input type="email" name="email" value="<%=dto.getEmail() %>" />
			</div>
			<div>
				<label>프로필 이미지</label>
				<div>
						<%if(dto.getProfileImage() == null) { %>
						
						<%}else{ %>
						
						<%} %>
				</div>
				<input type="file" name="profileImage" accept="image/*"/>
			</div>
			<button type="submit">수정확인</button>
			<button type="reset">취소</button>
		</form>
	</div>
</body>
</html>






