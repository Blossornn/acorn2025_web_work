<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
 // 세션에 "userName" 이라는 키 값으로 저장된 값이 있는 지 읽어와 본다. 
String userName =(String)session.getAttribute("userName");

%>
<!DOCTYPE html>
<html> 
<head>
<meta charset="UTF-8">
<title>/index.jsp</title>

<jsp:include page="/WEB-INF/include/resource.jsp"></jsp:include>

<style>
body {
	padding-top: 70px;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/include/navbar.jsp">
		<jsp:param value="index" name="thisPage" />
	</jsp:include>
	<div class="container">
		<h1>인덱스 페이지 입니다</h1>
		<ul>
			<li><a href="${pageContext.request.contextPath}/admin/index.jsp">관리자페이지</a></li>
			<li><a href="${pageContext.request.contextPath}/staff/index.jsp">직원</a></li>
		</ul>
		<div id="carouselExampleIndicators" class="carousel slide">
			<div class="carousel-indicators">
				<button type="button" data-bs-target="#carouselExampleIndicators"
					data-bs-slide-to="0" class="active" aria-current="true"aria-labeldu"Slide 1"></button>
				<button type="button" data-bs-target="#carouselExampleIndicators"
					data-bs-slide-to="1" aria-label="Slide 2"></button>
				<button type="button" data-bs-target="#carouselExampleIndicators"
					data-bs-slide-to="2" aria-label="Slide 3"></button>
			</div>
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="images/top01.jpg" class="d-block w-100" alt="...">
				</div>
				<div class="carousel-item">
					<img src="images/top02.jpg" class="d-block w-100" alt="...">
				</div>
				<div class="carousel-item">
					<img src="images/top03.jpg" class="d-block w-100" alt="...">
				</div>
			</div>
			<button class="carousel-control-prev" type="button"
				data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next" type="button"
				data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Next</span>
			</button>
		</div>
	</div>
	<jsp:include page="/WEB-INF/include/footer.jsp"></jsp:include>
</body>
</html>