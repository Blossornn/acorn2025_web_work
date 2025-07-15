package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import test.dao.MemberDao;
import test.dto.MemberDto;

@WebServlet("/members")
public class MemberServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<MemberDto> list = new MemberDao().selectAll();

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		PrintWriter pw = response.getWriter();
		pw.println("<!doctype html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset=\"utf-8\">");
		pw.println("<title>회원목록</title>");
		pw.println("<style>");
		pw.println("table { border-collapse: collapse; width: 80%; }");
		pw.println("th, td { border: 1px solid #999; padding: 8px; }");
		pw.println("th { background-color: #eee; }");
		pw.println("</style>");
		pw.println("</head>");
		pw.println("<body>");
		for (int i = 0; i < 5; i++) {
			pw.println("<p>Hello" + i + "</p>");
		}
		pw.println("<h1>회원 목록</h1>");
		pw.println("<table>");
		pw.println("<thead><tr><th>번호</th><th>이름</th><th>주소</th></tr></thead>");
		// for문 돌면서 List<MemberDto> 객체 안에 모든 회원 정보를 tr로 구성 해 출력
		pw.println("<tbody>");
		for (MemberDto tmp : list) {
			pw.println("<tr>");
			pw.println("<td>" + tmp.getNum() + "</td>");
			pw.println("<td>" + tmp.getName() + "</td>");
			pw.println("<td>" + tmp.getAddr() + "</td>");
			pw.println("</tr>");
		}
		pw.println("</tbody>");
		pw.println("</table>");
		pw.println("</body>");
		pw.println("</html>");

		pw.close();
	}
}