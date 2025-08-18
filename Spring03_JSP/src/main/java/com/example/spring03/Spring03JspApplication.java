package com.example.spring03;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
/*
 *  1. application.properties 설정 파일을 읽어들여서 동작을 준비한다.
 *  
 *  2. 현재 이 클래스가 존재하는 패키지를 포함해서 모든 하위 패키지를 scan 에서 bean으로 만들 것들은 만들고
 * 	   필요하다면 의존성 주입도 실행한다.
 * 
 *  3. 이 프로젝트는 spring web 프로젝트 이기 때문에 내장 tomcat도 실행 해서 서비스를 준비한다.
 */
@SpringBootApplication
public class Spring03JspApplication {
	// Run as Spring Boot App 메뉴를 선택해서 실행하면 여기가 실행 된다.
	public static void main(String[] args) {
		SpringApplication.run(Spring03JspApplication.class, args);
	}
	
}
