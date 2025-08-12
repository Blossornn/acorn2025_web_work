<%@page import="test.dto.UserDto"%>
<%@page import="test.dao.UserDao"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	// get방식 파라미터로 넘어오는 입력한 id 을 읽어온다.
	String inputId = request.getParameter("inputId");
	// dao 를 이용해서 해당 정보가 있는지 select 해보기 (null 일 가능성 있음)
	UserDto dto = UserDao.getInstance().getByUserName("userName");
	// 아이디 사용 가능 여부 알아내기
	boolean canUse = dto == null ? true : false;
%>

{"canUse" : <%=canUse %>} 