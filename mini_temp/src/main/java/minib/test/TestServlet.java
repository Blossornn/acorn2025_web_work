package minib.test;

import java.io.IOException;
import java.sql.Connection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import minib.util.DbcpBean;

@WebServlet("/dbtest")
public class TestServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 Connection conn = null;
	        try {
	            // DbcpBean을 통해 커넥션 얻기
	            conn = new DbcpBean().getConn();

	            if (conn != null) {
	                resp.getWriter().println("DBCP is working properly.");
	            } else {
	                resp.getWriter().println("DBCP is NOT working.");
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            resp.getWriter().println("error: " + e.getMessage());
	        } finally {
	            try { if (conn != null) conn.close(); } catch (Exception e) {}
	        }
	}
}
