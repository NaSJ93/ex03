package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.RankVO;
import org.zerock.domain.TimeVO;

public interface BoardMapper {
	
	//@Select("select * from tbl_board")
	//게시글 목록보기
	List<BoardVO> getList();
	
	//게시글 상세보기
	BoardVO read(Long bno);	
	
	//insert,update,delete시에는 리턴 타입을 int로 하면 처리된 행의 개수가 리턴된다.
	
	//게시글 작성
	void insert(BoardVO vo);	
	
	//게시글 작성(작성된 글 번호 확인)
	void insertSelectKey(BoardVO vo);
	
	//게시글 수정
	int update(BoardVO vo);
	
	//게시글 삭제
	int delete(Long bno);
	
	//보너스(작성글 랭킹 5등까지만 작성자,작성글 개수)
	List<RankVO> rank();	//어처피 한명이 오니까 int로 할 필요가 없 
	
	//보너스(수정일기준 최근 5개)
	List<BoardVO> lastBoard();
	
	//보너스(작성일 기준 시간대별 글계수 통계) 00 1 /1 1
	List<TimeVO> statTime();
	
	//전체 글개수(검색에도 대응) 가져오기
	Long count(Criteria cri);
	
	//게시글 페이지 처리 - 현재는 무조건 2페이지 출력(한페이지당 10개)
	List<BoardVO> getListWithPaging(Criteria cri);
	
	//좋아요 저장
	void insertGood(Long bno);
	
	//좋아요 갯수
	Long countGood(Long bno);
	
	//게시글 검색
	

}
