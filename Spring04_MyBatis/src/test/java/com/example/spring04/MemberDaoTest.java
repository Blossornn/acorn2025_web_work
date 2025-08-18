package com.example.spring04;

// Asserions 클래스에 존재하는 모든 static 자원을 import 해서 마치 이 클래스의 멤버 메소드처럼 사용 할 수 있음
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring04.dto.MemberDto;
import com.example.spring04.repository.MemberDao;

@SpringBootTest
@Transactional // 트랜잭션을 관리하도록 하고
@Rollback // insert, delete, update 등의 동작은 rollback 되도록 한다
public class MemberDaoTest {
	// 필요한 의존 객체 주입 받기
	@Autowired
	private MemberDao dao;
	
	@DisplayName("MemberDao 가  bean으로 관리 되는지 여부")
	@Test
	void test01 () {
	assertNotNull(dao);
	}
	
	// 이름과 주소를 전달하면 전달 된 정보를 DB에 저장하고 해당 정보의 PK 를 리턴 해 주는 메소드
	int insertFixture(String name, String addr) {
		MemberDto dto  = new MemberDto();
		dto.setName(name);
		dto.setAddr(addr);
		dao.insert(dto);
		// mybatis에서 selectKey 설정을 했기 때문에 insert 하고 나면 dto 객체에 추가 한 회원의 번호가 들어있다.
		return dto.getNum();
	}
	
	@Test
	@DisplayName("회원 정보 삭제 테스트")
	void delete() {
		int num = insertFixture("acorn","seoul");
		// 삭제하고 삭제 된 row 의 갯수를 리턴  받는다.
		int rows = dao.deleteByNum(num);
		// 삭제 된 row의 갯수는 반드시 1이어야 한다.
		assertEquals(1, rows);
		// select 된 정보는 반드시 null 이어야 한다.
		assertNull(dao.getByNum(num));
	}
	
	@Test
	@DisplayName("회원 정보 추가 테스트")
	void insert() {
		// 미리 만들어 둔 메소드로 데이터를 넣고 PK 를 리턴 받아서 사용한다.
		int num = insertFixture("acorn", "seoul");
		// 데이터가 잘 들어 갔는지 select 해보기
		MemberDto found = dao.getByNum(num);
		// select 된 정보가 null이 아니여야 한다.
		assertNotNull(found);
		//select 된 정보의 name 이 반드시 "acorn" 이어야 한다
		assertEquals("acorn", found.getName());
		//select 된 정보의 addr 이 반드시 "seoul" 이어야 한다
		assertEquals("seoul", found.getAddr());
	}
	@Test
	@DisplayName("회원 목록 테스트")
	void selectAll2() {
		int num = insertFixture("acorn", "seoul");
		// 회원 목록 select 해서 목록에 방금 추가 된 정보가 잘 들어 있는 지 확인 하기
		List<MemberDto> list = dao.selectAll();
		/*
		 *  item.getNum() == num 이 하나라도 true 가 있다면 (하나라도 일치한다면)
		 *  true 를 리턴하는 메소드가 . anyMatch() 메소드 이다.
		 */
		boolean isExist =  list.stream().anyMatch(item -> item.getNum() == num);
		
		// isExist 는 반드시 true여야 한다.
		assertTrue(isExist);
		
	}
	@Test
	@DisplayName("회원 목록 테스트")
	void selectAll() {
		int num = insertFixture("acorn", "seoul");
		// 회원 목록 select 해서 목록에 방금 추가 된 정보가 잘 들어 있는 지 확인 하기
		List<MemberDto> list = dao.selectAll();
		boolean isExist = false;
		for(MemberDto tmp : list) {
			if(tmp.getNum() == num) {
				isExist = true; // 하나라도 true가 나오면 값을 변수에 담고
				break; // 반복문 탈출
			}
		}
		// isExist 는 반드시 true여야 한다.
		assertTrue(isExist);
	}
	
	
	@Test
	@DisplayName("회원 정보 업데이트 테스트")
	void update() {
		int num = insertFixture("acorn", "seoul");
		 
		MemberDto dto = dao.getByNum(num);
		// 필드를 수정하고
		dto.setName("acorn2");
		dto.setAddr("home");
		
		int rows = dao.update(dto);
		MemberDto dto2 = dao.getByNum(num);
		
		assertEquals(1, rows);
		assertEquals("acorn2", dto2.getName());
		assertEquals("home", dto2.getAddr());
		
		
	}

}