<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%--
    	JSTL 사용하기
    	uri = "import" 할 경로
    	prefix = "접두어"
     --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
// 테스트를 위한 sample 데이터를 request scope에 담기
List<String> names = new ArrayList<>();
names.add("김구라");
names.add("해골");
names.add("원숭이");
// "list" 라는 키 값으로 request scopre 에 ArrayList 객체 담아두기
request.setAttribute("list", names);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/jstl/test01.jsp</title>
</head>
<body>
	<%
	//request scope = "list" 라는 키 값으로 저장 된 친구목록 얻어내기
	List<String> list = (List<String>)request.getAttribute("list");
	
	%>
		<h1>친구 목록</h1>
	<ul>
		<%for(String tmp : list) { %>
			<li><%=tmp %></li>
		<%} %>
	</ul>
	
	<h1>EL 과 JSTL 을 활용해서 위와 동일한 동작</h1>
	<ul>
		<c:forEach var="tmp" items="${requestScope.list}" >
		<li>${tmp }</li>
		</c:forEach>
	</ul>
	
	<h1>친구 목록 순서 표시</h1>
	<ul>
	 <c:forEach var="tmp" items="${list }" varStatus="status">
	 	<li>${tmp } <strong>순서 : ${status.count }</strong></li>
	 </c:forEach>
	</ul>
	<h1>친구 목록 첫 번째 순서인지 여부</h1>
	<ul>
	<c:forEach var="tmp" items="${list }" varStatus="status">
		<li>${tmp } 
		<strong>첫 번째 : ${status.first }</strong>
	 	<c:if test="${status.first }">
	 	제일 잘 생긴 친구
		</c:if>
		</li>
	</c:forEach>
	</ul>
		<h1>친구 목록 마지막 번째 순서인지 여부</h1>
	<ul>
	<c:forEach var="tmp" items="${list }" varStatus="status">
		<li>${tmp } 
		<strong>마지막 번째 : ${status.last }</strong>
		<c:if test="${status.last }">
	 	제일 마지막 친구
		</c:if>
		</li>
	</c:forEach>
	</ul>
</body>
</html>