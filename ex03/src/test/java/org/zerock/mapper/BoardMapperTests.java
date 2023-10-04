package org.zerock.mapper;

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
public class BoardMapperTests {
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void TestgetList() {
		boardMapper.getList().forEach(x->log.info(x));
	}
	
		
	@Test
	public void TestRead() {				//Long값이라서 그냥 숫자를 넣으면 int로 인식함(그래서 L을 추가) 
		log.info("2번글 읽기"+boardMapper.read(2L));
	}
	
	@Test
	public void TestInsert() {
		BoardVO vo=new BoardVO();
		vo.setTitle("mapper테스트");
		vo.setContent("테스터");
		vo.setWriter("mapper트");		
		boardMapper.insert(vo);
	}
	
	@Test
	public void TestInsertSelectKey() {
		BoardVO vo=new BoardVO();
		vo.setTitle("mapper테스트");
		vo.setContent("테스터");
		vo.setWriter("mapper트");		
		boardMapper.insertSelectKey(vo);
		log.info("입력된 글번호는:"+vo.getBno());
	}
	
	@Test
	public void TestUpdate() {
		BoardVO vo=new BoardVO();
		vo.setBno(1L);
		vo.setTitle("바꿈");
		vo.setContent("너도바꿈");
		log.info("수정결과:"+boardMapper.update(vo));		
	}
	
	@Test
	public void TestDelete() {
		log.info("삭제결과:"+boardMapper.delete(6L));
	}
	
	@Test	//더미 데이터 넣기
	public void inserData() {
		BoardVO vo=new BoardVO();			
		for(int i=0;i<5;i++) {
			vo.setTitle("mapper테스트"+i);
			vo.setContent("테터"+i);
			vo.setWriter("테스터"+i);
			boardMapper.insert(vo);	
		}		
	}
	
	@Test
	public void TestRank() {
		boardMapper.rank().forEach(x->log.info(x));
	}
	
	
	@Test
	public void TestCount() {
		Criteria cri =new Criteria();
		log.info("전체글 개수 : "+boardMapper.count(cri));
		cri.setType("CW");
		cri.setKeyword("테스");
		log.info("검색글 개수 : "+boardMapper.count(cri));
		
	}
	
	@Test
	public void TestLastBoard() {
		boardMapper.lastBoard().forEach(x->log.info(x));
	}
	
	@Test  //여기까지 197p
	public void TeststatTime() {
		//boardMapper.statTime().forEach(x->log.info(x));
		log.info("시간대별 통계:"+boardMapper.statTime());
	}	
	
	@Test
	public void TestGetListWithPaging() {
		Criteria cri=new Criteria();
		cri.setType("W");
		cri.setKeyword("테스");
		boardMapper.getListWithPaging(cri).forEach(x->log.info("결과확인:"+x));
	}
	
	@Test
	public void TestinsertGood() {		
		boardMapper.insertGood(3L);
	}
	
	@Test
	public void TestcountGood() {
		log.info("좋아요 개수"+boardMapper.countGood(3L));
	}
}
