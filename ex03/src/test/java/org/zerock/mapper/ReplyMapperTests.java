package org.zerock.mapper;

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
public class ReplyMapperTests {
	
	@Autowired
	private ReplyMapper replyMapper;
	
	@Test
	public void TestgetList() {
		replyMapper.getList(996L).forEach(x->log.info(x));
	}	
		
	
	@Test
	public void TestInsert() {
		ReplyVO vo=new ReplyVO();
		vo.setBno(996L);
		vo.setReply("테스댓");
		vo.setReplyer("테스댓작");	
		replyMapper.insert(vo);
		log.info("입력된 글번호"+vo.getBno());
	}	
	
	
	@Test
	public void TestUpdate() {
		ReplyVO vo=new ReplyVO();
		vo.setRno(8L);
		vo.setReply("수정한댓글");			
		log.info("수정결과:"+replyMapper.update(vo));		
	}
	
	@Test
	public void TestDelete() {
		log.info("삭제결과:"+replyMapper.delete(6L));
	}
	
	
}
