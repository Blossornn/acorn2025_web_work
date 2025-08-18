package com.example.spring02.dto;

import lombok.Getter;
import lombok.Setter;

// lombok 의 기능을 이용해서 getter/setter 메소드가 만들어 지게 한다.
@Getter
@Setter
public class MemberDto {
	private int num;
	private String name;
	private String addr;
}
