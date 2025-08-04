package test.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator.RequestorType;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/test/fileup")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 업로드 처리하기 위한 메모리 사이즈 ( 10 Mega byte)
		maxFileSize = 1024 * 1024 * 50, // 업로드되는 최대 파일 사이즈 (50Mega byte)
		maxRequestSize = 1024 * 1024 * 60 // 이 요청의 최대 사이즈 (60Mega byte), 파일 외의 다른 문자열도 전송 되기 때문
)
public class FileUpServlet extends HttpServlet {
	// 업로드 된 파일 저장 경로를 저장 할 필드 선언
	String fileLocation;

	// 이 서블릿이 초기화되는 시점에 최초 1 번 호출되는 메소드
	@Override
	public void init() throws ServletException {
		// 무언가 초기화 작업을 여기서 하면 된다.
		ServletContext context = getServletContext();
		// web.xml 파일에 "fileLocation" 이라는 이름으로 저장된 정보를 읽어와서 필드에 저장
		fileLocation = context.getInitParameter("fileLocation");
		// /Users/koyoungseok/playGround/upload 에 저장 될 예정
	}

	// post 방식 요청만 처리 할 거라면 doPost() 메소드 오버라이드
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * "caption" 이라는 파라미터 명으로 문자열이 전송되고 "myFile" 이라는 파라미터 명으로 파일이 전송된다. (추출 방식이 다름)
		 */
		String caption = req.getParameter("caption");
		// <input Type = "file" name= "myFile">
		Part filePart = req.getPart("myFile");
		if (filePart != null && filePart.getSize() > 0) {
			// 원본 파일의 이름 얻어내기
			String orgFileName = filePart.getSubmittedFileName();
			// 파일명이 겹치지 않게 랜덤한 id 값 얻어내기
			String uid = UUID.randomUUID().toString();
			// 저장 될 파일명을 구성한다
			String saveFileName = uid + orgFileName;
			// 저장 할 파일의 경로 구성
			String filePath = fileLocation + "/" + saveFileName;
			/*
			 * 업로드 된 파일은 임시 폴더에 임시 파일로 저장이 된다 해당 파일에서 byte 알갱이를 읽어 들일 수 있는 InputStream 객체를
			 * 얻어내서
			 */ 
			InputStream is = filePart.getInputStream();
			// 원하는 목적지에 copy 해야 한다.
			Files.copy(is, Paths.get(filePath));
			// 업로드 된 파일의 크기
			long fileSize = filePart.getSize();
			
			/*
			 *  원래는 DB에 저장해야 하지만 테스트로 응답에 필요한 데이터를 
			 *  HttpServletRequest 객체의 .setAttribute(키, 값) 메소드를 이용해서 저장하고 
			 *  jsp 페이지가 사용할 수 있도록 한다.
			 *  jsp 페이지에서 해당 정보를 얻어낼 때는
			 *  HttpServletRequest 객체의 .getAttribute(키) 메소드를 이용해서 얻어낸다.
			 *  Object type 으로 리턴되기 때문에 원래 type으로 casting을 해야 한다.
			 */
			req.setAttribute("orgFileName", orgFileName); // String
			req.setAttribute("saveFileName", saveFileName); // String
			req.setAttribute("fileSize", fileSize); // long
			
		} //end  if
		
		
		// 요청 전달자 객체 얻어내기
		RequestDispatcher rd = req.getRequestDispatcher("/test/윤제.jsp");
		// 응답을 위임하기
		rd.forward(req, resp);
		
	} //end  method

} //end class
