package test.filter;
/*
 *  웹 서버의 보안을 담당할 보안 필터 만들
 */

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Set;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// 들어오는 모든 요청에 대해서 필터링 하겠다는 의미
@WebFilter("/*")
public class SecurityFilter implements Filter {
	// 로그인 없이 접근 가능한 경로 목록
	Set<String> whiteList = Set.of("/index.jsp", "/user/loginform.jsp", "/user/login.jsp", "/user/signup-form.jsp",
			"/user/signup.jsp", "/images/","/upload/","/board/list.jsp","/board/view.jsp"
			);
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 로그인을 했는 지 확인 작업
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// HttpSession 객체의 참조값 얻어내기
		HttpSession session = req.getSession();
		// context path
		String cPath = req.getContextPath();

		// 클라이언트의 요청 경로 얻어내기
		String uri = req.getRequestURI(); // ex:/member/list.jsp

		// uri에서 context path를 제거한 순수 경로를 얻어낸다.
		String path = uri.substring(cPath.length());

		// 로그인 없이 접근 가능한 요청 경로면 필터링 하지 않는다.
		if (isWhiteList(path)) {
			chain.doFilter(req, response);
			return; // 여기서 메소드를 종료한다.

		}

		// 로그인 여부 확인
		String userName = (String) session.getAttribute("userName");
		// role 정보
		String role = (String) session.getAttribute("role");
		
		// 만일 로그인을 하지 않았다면
		if (userName == null) {
			// 로그인 페이지로 리다일렉트(새로운 경로로 요청을 다시하라고 응답) 이동 시킨다
			// query 문자열이 있으면 읽어와서
			String query = req.getQueryString();
			// 인코딩을 한다음
			String encodedUrl = query == null ? URLEncoder.encode(uri, "UTF-8")
					: URLEncoder.encode(uri + "?" + query, "UTF-8");
			// 리다일렉트 되는 경로뒤에 url 이라는 파라미터명으로 전달한다
			res.sendRedirect(req.getContextPath() + "/user/loginform.jsp?url=" + encodedUrl);
			return; // 메소드를 여기서 끝내기
		}
		// 권한 체크
		// 만일 isAuthorized가 false를 리턴한다면 ( 접근 불가하다고 판정이 된다면)
		if(!isAuthorized(path, role)) {
			// 금지된 요청이라고 응답
			res.sendError(HttpServletResponse.SC_FORBIDDEN, "접근 권한이 없습니다.");
			return; // 여기서 끝낸다 메소드를
		}
		
		chain.doFilter(request, response);

	}

	// 화이트리스트 검사
	private boolean isWhiteList(String path) {
		if ("/".equals(path))
			return true;

		// 반복문 돌면서 모든 whiteList 를 불러내서
		for (String prefix : whiteList) {
			// 현재 요청 경로와 대조한다
			if (path.startsWith(prefix)) {
				return true;
			}
		}
		return false;
	}
    // 역할 기반 권한 검사
	// 클라이언트의 요청 경로와 role 정보를 넣어서 접근 가능한 지 여부를 리턴하는 메소드
    private boolean isAuthorized(String path, String role) {
        if ("ROLE_ADMIN".equals(role)) {
            return true; // 모든 경로 접근 허용
        } else if ("ROLE_STAFF".equals(role)) {
        // "/admin/" 하위 경로를 제외한 모든 경로 접근 허용	
            return !path.startsWith("/admin/");
        } else if ("ROLE_USER".equals(role)) {
        	// "/admin/" 하위와 "/staff/" 하위를 제외한 모든 경로 접근 허용
            return !path.startsWith("/admin/") && !path.startsWith("/staff/");
        }
        return false; // unknown role
    }

}
