<%@page import="test.dto.MemberDto"%>
<%@page import="test.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    int num = Integer.parseInt(request.getParameter("num"));
    MemberDto dto = new MemberDao().getByNum(num);
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>회원 정보 수정</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" 
        integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
  <!-- Bootstrap Icons -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
  <div class="container mt-5">
    <div class="card shadow-sm">
      <div class="card-header bg-primary text-white">
        <h4 class="mb-0"><i class="bi bi-person-lines-fill"></i> 회원 정보 수정</h4>
      </div>
      <div class="card-body">
        <form action="${pageContext.request.contextPath}/member/update.jsp" method="post">
          <div class="mb-3">
            <label for="num" class="form-label">번호</label>
            <input type="text" class="form-control" name="num" id="num" value="<%=dto.getNum()%>" readonly />
          </div>
          <div class="mb-3">
            <label for="name" class="form-label">이름</label>
            <input type="text" class="form-control" name="name" id="name" value="<%=dto.getName()%>" required />
          </div>
          <div class="mb-3">
            <label for="addr" class="form-label">주소</label>
            <input type="text" class="form-control" name="addr" id="addr" value="<%=dto.getAddr()%>" required />
          </div>
          <div class="d-flex justify-content-between">
            <button type="submit" class="btn btn-primary">
              <i class="bi bi-pencil-square"></i> 수정
            </button>
            <button type="reset" class="btn btn-secondary">
              <i class="bi bi-arrow-clockwise"></i> 초기화
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</body>
</html>
