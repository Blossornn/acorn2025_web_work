package com.example.spring04.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spring04.dto.MemberDto;
import com.example.spring04.repository.MemberDao;
import com.example.spring04.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	// 필요한 의존 객체를 주입 받는다
	 private  final MemberService service;
	
	@PostMapping("/member/update")
	public String update(MemberDto dto) {
		
		service.updateMember(dto);
		
		return "member/update";
	}
	
	
	@GetMapping("/member/edit")
	public String edit(int num, Model model) {
		
		// 수정 할 회원의 정보를 얻어와서
		MemberDto dto = service.getMember(num);
		// Model 객체에 담고
		model.addAttribute("dto", dto);
		// view page 로 forward 이동해서 응답
		return "member/edit";
	}
	
	
	@GetMapping("/member/delete")
	public String delete(int num) {
		/*
		 *  매개변수에 int num 을 선언하면 요청 파라미터 중에서 num 이라는 파라미터 명으로 전달되는
		 *  문자열을 자동 추출해서 Integer.parseInt() 를 수행해서 실제 int 값으로 바꾼 다음
		 *  해당 값을 매개 변수에 전달 해 준다
		 *  int 값으로 바꿀 수 없는 문자열이 넘어오면 에러가 발생한다.
		 */
		service.deleteMember(num);
		return "member/delete";
	}
	
	@PostMapping("/member/save")
	public String save(MemberDto dto) {
		/*
		 *  매개 변수에 MemberDto 를 선언하면 폼 전송되는 파라미터가 자동으로 추출 되어서 
		 *  MemberDto 객체에 담긴 채로 전달 된다
		 *   단 MemberDto 클래스의 필드 명과 폼 전송 되는 파라미터 명이 일치해야 한다.
		 *   private String name <=> <input type = "text" name = "name">
		 *   private String addr <=> <input type = "text" name = "addr">
		 */
		service.addMember(dto);
		
		// 회원 목록보기 "/member/list" 요청을 다시 하라는 redirect 응답 하기
		// redirect: 리다이렉트 경로 처럼 redirect: 으로 시작 하는 문자열을 리턴하면 된다.
		return "redirect:/member/list";
	}
	
	@GetMapping("member/new-form")
	public String newForm() {
		return "member/new-form";
	}
	
	
	@GetMapping("member/list")
	public String list(Model model) {
		// 회원 목록
		List<MemberDto> list = service.getAll();
		// 응답에 필요한 객체를 Model 객체에 담는다.
		model.addAttribute("list", list);
		
		// "/WEB-INF/views/member/list.jsp" 에서 응답하기
		return "member/list";
	}
	
}
