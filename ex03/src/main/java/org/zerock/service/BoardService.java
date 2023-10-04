package org.zerock.service;

import java.util.Date;
import java.util.List;


import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardService {	//198p
	//사용자 관점의 서비스
	
	//1.게시글 목록보기
	List<BoardVO> getList(Criteria cri);
	
	//2.게시글 작성
	void register(BoardVO vo);
	
	//3.게시글 수정
	boolean modify(BoardVO vo);
	
	//4.게시글 삭제
	boolean remove(Long bno);
	
	//5.게시글 상세보기
	BoardVO get(Long bno);
	
	//전체 글개수 가져오기
	Long count(Criteria cri);
	
	//좋아요 저장
	void insertGood(Long bno);
		
	//좋아요 갯수
	Long countGood(Long bno);
	
	Date date();
	
}
