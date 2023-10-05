package org.zerock.service;

import java.util.List;

import org.zerock.domain.ReplyVO;

public interface ReplyService {
	
		//댓글작성
		int register(ReplyVO vo);
		
		//해당 글에 대한 댓글보기
		List<ReplyVO> getList(Long bno);

		//댓글수정
		int modify(ReplyVO vo);
		
		//댓글삭제
		int remove(Long rno);
		
		//댓글 한게 보기
		ReplyVO get(Long rno);	//get /replies/2
		
		//
		int count(Long bno);

}
