<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
// 1. form 전송 되는 숫자를 읽어와서 실제 정수로
int num = Integer.parseInt(request.getParameter("inputNum"));
// 2. 해당 숫자가 짝수이면 "전송한 숫자 x 는 짝수입니다."
//	  해당 숫자가 홀수이면 "전송한 숫자 x 는 홀수입니다."
//    를 콘솔 창이 아닌 클라이언트 웹 브라우저에 출력


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/jsp/save.jsp</title>
</head>
<body>	
<h3>if-else 문 이용</h3>
<%if(num % 2==0) {%>
<p>입력한 숫자 <%=num %> 은 짝수입니다.</p>
<%}else{ %>
<p>입력한 숫자 <%=num %> 은 홀수입니다.</p>
<%} %>

<h3>3항 연산자 이용</h3>
<p>입력한 숫자 인 <%=num%> 은 <%= num % 2==0 ? "짝수" : "홀수" %> 입니다. </p>

</body>
</html>