package org.zerock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")	
@Log4j
public class BoardSeviceTests {			//여기까지 209p
	
	@Autowired
	private BoardService service;
	
	@Test
	public void TestGetList() {
		//Criteria cri=new Criteria();
		log.info("리스트서비스:"+service.getList(new Criteria(2,4)));
	}
	
	@Test
	public void TestRegister() {		
		BoardVO vo=new BoardVO();
		vo.setTitle("mapper테스트2");
		vo.setContent("테스터2");
		vo.setWriter("mapper트2");
		log.info("게시글 작성서비스:");
		service.register(vo);
	}
	
	@Test
	public void TestModify(){
		BoardVO vo=new BoardVO();
		vo.setTitle("mapp테스트2");
		vo.setContent("테터2");
		vo.setBno(3L);
		log.info("수정 작성서비스:"+service.modify(vo));		
	}
	
	@Test
	public void TestRemove() {
		log.info("삭제결과:"+service.remove(13L));		
	}
	
	@Test
	public void TestGet() {
		log.info("상세 읽기"+service.get(2L));
	}
	
	@Test
	public void TestCount() {
		log.info(service.count(new Criteria()));
	}
	
	@Test
	public void TestDate() {
		log.info(service.date());
	}
}
