<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/jstl/test02.jsp</title>
</head>
<body>
	<c:if test="true">
		<p>이 문장이 출력될까?</p>
	</c:if>
	<c:if test="false">
		<p>요건 출력이 안되나?</p>
	</c:if>
	<%--el을 이요해서 연산의 결과를 jstl이 사용하도록 한다. --%>
	<c:if test="${10%2 == 0}">
		<p>10은 짝수 입니다</p>
	</c:if>
	<c:if test="${10%2 eq 0}"> <%--비교 연산자 eq는 ==과 같다 --%>
		<p>10은 짝수 입니다</p>
	</c:if>
	<c:if test="${20 > 10  }">
		<p>20 은 10보다 크다 ${20 > 10 }</p>
	</c:if>
	<c:if test="${20 gt 10 }">
	 	<p>20 은 10보다 크다 ${20 gt 10 }</p> <%--비교 연산자 gt는 >와 같다 --%>
	</c:if>
	<c:if test="${10 != 20 }">
		<p>10 과 20은 다르다 ${10 ne 20 }</p> <%-- 비교 연산자 ne는 !-와 같다 --%>
	</c:if>
	<c:if test="${'kim' ne 'park' }">
		<p>kim 과 park은 다르다</p>
	</c:if>
</body>
</html>