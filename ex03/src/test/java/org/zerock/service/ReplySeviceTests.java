package org.zerock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.ReplyVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")	
@Log4j
public class ReplySeviceTests {			//여기까지 209p
	
	@Autowired
	private ReplyService service;
	
	@Test
	public void TestGetList() {
		log.info("리스트서비스:"+service.getList(3L));
	}
	
	@Test
	public void TestRegister() {		
		ReplyVO vo=new ReplyVO();
		vo.setBno(999995L);
		vo.setReply("서비스댓");
		vo.setReplyer("서비스댓작");
		log.info("댓글 작성서비스:");
		service.register(vo);
	}
	
	@Test
	public void TestModify(){
		ReplyVO vo=new ReplyVO();
		vo.setRno(1L);
		vo.setReply("서비스수정");
		log.info("수정 작성서비스:"+service.modify(vo));		
	}
	
	@Test
	public void TestRemove() {
		log.info("삭제결과:"+service.remove(12L));		
	}	
	
}
