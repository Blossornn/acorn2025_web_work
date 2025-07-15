<%@page import="test.dto.MovieDto"%>
<%@page import="java.util.List"%>
<%@page import="test.dao.MovieDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
MovieDao dao = new MovieDao();
List<MovieDto> list = dao.selectAll();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 목록 보기</title>

<!-- 구글 폰트 - League Gothic 느낌 유사 -->
<link href="https://fonts.googleapis.com/css2?family=Cinzel:wght@600&display=swap" rel="stylesheet">

<style>
    body {
        background-color: #0a0a0a;
        color: #e0d8c3;
        font-family: 'Cinzel', serif;
        margin: 0;
        padding: 0;
    }

    h1 {
        text-align: center;
        color: #c89b3c;
        font-size: 36px;
        margin: 20px 0;
        text-shadow: 2px 2px 4px #000;
    }

    .container {
        width: 80%;
        margin: auto;
        background-color: #1c1c1c;
        padding: 20px;
        border: 2px solid #c89b3c;
        border-radius: 10px;
        box-shadow: 0 0 20px rgba(200, 155, 60, 0.4);
    }

    a {
        color: #00aaff;
        text-decoration: none;
        font-weight: bold;
    }

    a:hover {
        color: #ffcc00;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }

    th, td {
        border: 1px solid #444;
        padding: 10px;
        text-align: center;
    }

    th {
        background-color: #111;
        color: #c89b3c;
        font-size: 18px;
    }

    td {
        background-color: #222;
    }

    tr:hover td {
        background-color: #2c2c2c;
    }

    .btn {
        padding: 5px 10px;
        background-color: #005577;
        color: white;
        border-radius: 5px;
        display: inline-block;
    }

    .btn:hover {
        background-color: #0077aa;
    }

    .btn.delete {
        background-color: #aa0000;
    }

    .btn.delete:hover {
        background-color: #ff3333;
    }
</style>
</head>
<body>
<h1>영화 목록 🎬</h1>
<div class="container">
    <a href="${pageContext.request.contextPath}/movie/insertform.jsp" class="btn">🎥 영화 추가</a>

    <table>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>감독</th>
            <th>수정</th>
            <th>삭제</th>
        </tr>
        <% for(MovieDto dto : list) { %>
        <tr>
            <td><%=dto.getNum() %></td>
            <td><%=dto.getTitle() %></td>
            <td><%=dto.getPublisher() %></td>
            <td><a class="btn" href="updateform.jsp?num=<%=dto.getNum()%>">수정</a></td>
            <td><a class="btn delete" href="${pageContext.request.contextPath}/movie/delete.jsp?num=<%=dto.getNum() %>">삭제</a></td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>
