<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/movie/insertform.jsp</title>
</head>
<body>
	<div class="container">
		<h1>영화 추가하기!</h1>
		<form action="${pageContext.request.contextPath}/movie/insert.jsp"
			method="post">
			<div>
				<label for="title">제목</label> 
				<input type="text" name="title"
					id="title" placeholder="영화 제목을 적어주세요.." />
			</div>
			<div>
				<label for="publisher">감독</label>
				 <input type="text" name="publisher"
					id="publisher" />
			</div>
			<button type="submit">추가하기</button>
		</form>
	</div>
</body>
</html>