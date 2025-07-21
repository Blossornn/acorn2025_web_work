<%@page import="test.dto.BoardDto"%>
<%@page import="test.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	// 폼 전송되는 내용을 읽어와서
	int num = Integer.parseInt(request.getParameter("num"));
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	String writer = BoardDao.getInstance().getByNum(num).getWriter();
	String userName = (String) session.getAttribute("userName");
	// 만일 같지 않으면
	if (!writer.equals(userName)) {
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "남의 글 함부로 수정하면 혼나요");
		return;
	}
	// DB에 수정 반영하고 
	 BoardDto dto = new BoardDto();
	dto.setNum(num);
	dto.setTitle(title);
	dto.setContent(content);
	BoardDao.getInstance().update(dto);
	//응답한다
%>
<!DOCTYPE html>
<html> 
<head>
<meta charset="UTF-8">
<title>/board/update.jsp</title>
</head>
<body>
<script>
alert("수정 완료");
location.href="${pageContext.request.contextPath}/board/view.jsp?num=<%=num%>";
</script>

</body>
</html>