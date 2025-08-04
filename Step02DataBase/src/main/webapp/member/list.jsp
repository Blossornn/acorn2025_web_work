<%@page import="test.dao.MemberDao"%>
<%@page import="test.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@page import="test.util.DbcpBean"%>
<%@ page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
MemberDao dao = new MemberDao();
List<MemberDto> list = dao.selectAll();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
<jsp:include page="/WEB-INF/include/resource.jsp"></jsp:include>
<style>
body {
	padding: 70px;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/include/navbar.jsp">
		<jsp:param value="member" name="thisPage" />
	</jsp:include>
	<div class="container">
		<a href="${pageContext.request.contextPath}/member/insertform.jsp">회원 추가</a>
		<h1>회원목록</h1>
		<table class="table table-striped">
			<thead class="table-dark">
				<tr>
					<th>번호</th>
					<th>이름</th>
					<th>주소</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
				<%for (MemberDto dto : list) {%>
				<tr>
					<td><%=dto.getNum()%></td>
					<td><%=dto.getName()%></td>
					<td><%=dto.getAddr()%></td>
					<td><a href="updateform.jsp?num=<%=dto.getNum()%>">수정</a></td>
					<td><a href="javascript:" class="deleteLink" data-num="<%=dto.getNum()%>">삭제</a></td>
				</tr>
				<%}%>
			</tbody>
		</table>
		<a href="<%=request.getContextPath()%>/index.jsp">메인으로</a>
	</div>
	<jsp:include page="/WEB-INF/include/footer.jsp"></jsp:include>
	<script>
      document.querySelectorAll(".deleteLink").forEach(item => {
         item.addEventListener("click",(e)=>{
      
         const num = e.target.getAttribute("data-num");
         const isDelete = confirm(num + "번 삭제 하시렵니까?");
         
         if (isDelete){
            location.href = "${pageContext.request.contextPath}/member/delete.jsp?num=" +num;
         }
      });
    });
   </script>
</body>
</html>
