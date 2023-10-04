package org.zerock.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@AllArgsConstructor
@RequestMapping("/replies")
public class ReplyController {
	
	private ReplyService service;
	
	//확장 프로그램 테스트
	//http://localhost:8080/replies/new	   //{"bno":996,"reply":"포스트컨트롤러","replyer":"포스트테스트"}
	@PostMapping(value = "/new",produces="text/plain;charset=UTF-8")		 
	public String create(@RequestBody ReplyVO vo) {
		log.info("등록해야할 댓글정보");
		int count=service.register(vo);
	//	count==1 ? success
		//댓글이 등록되면 success 문자를 보내고 상태는 200(정상) / 실패하면 아무것도 안보내고 500
		//int 리턴 이므로 1이면 정상
		return "잘등록됨";
		
	}

	@DeleteMapping("/{rno}")
	public String urlDelete() {
		
		return null;
	}
	
	@PutMapping("/{rno}")
	public String urlPut() {
		return null;
	}
	
	@GetMapping("/pages/{bno}")
	public List<ReplyVO> urlList(){
		return null;
	}
	
}
