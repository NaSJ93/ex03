package org.zerock.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@AllArgsConstructor	//생성자 주입
@Log4j
@Service		//service를 위한 component(스프링에 service 객체로 인식시키기 위해서
public class BoardServiceImpl implements BoardService {
	
	
	private BoardMapper mapper;

	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("목록보기 서비스 요청..");
		//return mapper.getList();
		return mapper.getListWithPaging(cri);
	}

	@Override
	public void register(BoardVO vo) {
		log.info("글 등록서비스 요청..");
		//mapper.insert(vo);
		mapper.insertSelectKey(vo);
	}

	@Override
	public boolean modify(BoardVO vo) {
		log.info("글 수정 서비스 요청..");
		return mapper.update(vo)==1 ? true : false; //삼항연산자		
		/*
		if(mapper.update(vo)==1)
			return true;
		else
			return false;
			*/
	}

	@Override
	public boolean remove(Long bno) {
		log.info("글 삭제 서비스 요청..");
		return mapper.delete(bno)==1 ? true : false;		
	}

	@Override
	public BoardVO get(Long bno) {
		log.info("글 상세보기 서비스 요청..");
		return mapper.read(bno);
	}

	@Override
	public Long count(Criteria cri) {
		log.info("전체 글개수 서비스 요청..");
		return mapper.count(cri);
	}

	@Override
	public void insertGood(Long bno) {
		log.info("좋아요 추가");
		mapper.insertGood(bno);
		
	}

	@Override
	public Long countGood(Long bno) {
		log.info("좋아요 검색");
		return mapper.countGood(bno);
	}

	@Override
	public Date date() {
		Date date=new Date();
		return date;
	}

}
