package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.swing.text.html.HTML;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/fortune")
public class FortuneServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 운세 목록 20개
		String[] fortunes = {
			"동쪽으로 가면 귀인을 만나요!",
			"오늘은 운이 아주 좋습니다.",
			"새로운 기회를 잡을 수 있는 날이에요.",
			"오늘은 조금 조심해야 해요.",
			"뜻밖의 행운이 따를 거예요.",
			"과거의 노력이 결실을 맺습니다.",
			"사람들과의 만남이 행운을 줍니다.",
			"작은 실수가 큰 결과를 낳을 수 있어요.",
			"오늘은 휴식이 필요한 날입니다.",
			"당신의 아이디어가 주목받게 될 거예요.",
			"금전운이 좋습니다.",
			"좋은 소식을 듣게 될 거예요.",
			"일상에 작은 변화가 생길 거예요.",
			"무엇을 하든 자신감을 가지세요.",
			"오늘은 모험을 피해보세요.",
			"중요한 결정을 내리기 좋은 날입니다.",
			"몸과 마음의 균형을 잘 맞추세요.",
			"지나친 걱정은 오히려 독이 될 수 있어요.",
			"예상치 못한 만남이 기다리고 있어요.",
			"기회는 준비된 자에게 옵니다."
		};

		// 랜덤 인덱스 선택
		Random rand = new Random();
		String fortuneToday = fortunes[rand.nextInt(fortunes.length)];

		// 응답 인코딩 설정
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		// 응답 출력
		PrintWriter pw = response.getWriter();
		pw.println("<!doctype html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset=\"utf-8\">");
		pw.println("<title> 오늘의 운세 </title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<h2>✨ 오늘의 운세 ✨</h2>");
		pw.println("<p>" + fortuneToday + "</p>");
		pw.println("<img src ='Hello/images/SouthKorea.png'>");
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
		
	}
}
