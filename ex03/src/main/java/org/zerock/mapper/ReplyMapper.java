package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.ReplyVO;

public interface ReplyMapper {
	
	//댓글작성
	int insert(ReplyVO vo);
	
	//댓글읽기
	List<ReplyVO> getList(Long bno);

	//댓글수정
	int update(ReplyVO vo);
	
	//댓글삭제
	int delete(Long rno);
	
	//댓글 한게 보기
	ReplyVO get(Long rno);
	
	//
	int count(Long bno);

}
