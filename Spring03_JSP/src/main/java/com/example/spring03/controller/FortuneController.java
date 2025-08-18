package com.example.spring03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class FortuneController {
	@GetMapping("/fortune2")
	public String fortune2(Model model) {
		/*
		 *  HttpServletRequest 는 Http의 모든 기능을 다루는 객체
		 *  Model 은 view page 로 넘길 데이터만 담는 객체 (더 편리하게 사용 할 수 있다.)
		 *  Model 객체도 컨트롤러 메소드의 매개 변수에 선언만 하면 자동으로 spring 이 전달 해 준다.
		 */
		
		String fortuneToday = "동쪽으로 가면 귀인을 만나요!";
		// Model 객체에 담으면 자동으로 HttpServletRequest 객체에 담긴다
		model.addAttribute("fortuneToday", fortuneToday);
		return "fortune";
	}
	

    @GetMapping("/fortune")
    public String fortune(HttpServletRequest request) {
    	
       String fortuneToday = "서쪽으로 가면 장대비를 만나요!";
       request.setAttribute("fortuneToday", fortuneToday);
        
        return "fortune";
    }
}
