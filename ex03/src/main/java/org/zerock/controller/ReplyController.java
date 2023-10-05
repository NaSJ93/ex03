package org.zerock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	//consumes 통해 보내온 데이터 타입 확인 -> 틀리면 415(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@PostMapping(value = "/new",
			consumes =MediaType.APPLICATION_JSON_VALUE,	 
			produces="text/plain;charset=UTF-8")		 
	public ResponseEntity<String> create(@RequestBody ReplyVO vo) {		
		log.info("등록해야할 댓글정보");
		int count=service.register(vo);
		if(count==1)
			return new ResponseEntity<String>("success",HttpStatus.OK);			
		else {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		//HttpStatus.BAD_REQUEST 400에러		
		//댓글이 등록되면 success 문자를 보내고 상태는 200(정상) / 실패하면 아무것도 안보내고 500(에러)
		//int 리턴 이므로 1이면 정상
		
	}

	@DeleteMapping("/{rno}")
	public ResponseEntity<String> urlDelete(@PathVariable("rno") Long rno) {
		int count =service.remove(rno);
		if(count==1)
			return new ResponseEntity<String>("success",HttpStatus.OK);			
		else 
			return new ResponseEntity<String>(HttpStatus.I_AM_A_TEAPOT);
	}
	
	@PutMapping("/{rno}")
	public ResponseEntity<String> urlPut(@PathVariable("rno") Long rno, @RequestBody ReplyVO vo) {
		vo.setRno(rno);
		return (service.modify(vo)== 1) 
				? new ResponseEntity<String>("success",HttpStatus.OK) 
				: new ResponseEntity<String>(HttpStatus.I_AM_A_TEAPOT);
	}
	
	//ex /replies/pages/996
	@GetMapping(value = "/pages/{bno}",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ReplyVO> urlList(@PathVariable("bno") Long bno){		
		return service.getList(bno);
	}
	
	@GetMapping("/{rno}")
	public ReplyVO urlGet(@PathVariable("rno") Long rno) {
		return service.get(rno);
		
	}
	
	@GetMapping("/count/{bno}")
	public int count(@PathVariable("bno") Long bno) {
		return service.count(bno);
	}
	
	//		이건 불가능함 >>>왜냐하면 위에 @GetMapping("/{rno}")에 걸리기 때문 '/아무 문자'든 다 걸려버림 
	// ㄴㄴ get이어서 안됨 url에 값을 보내는 방식인데 post로 해야지 
/*	@PostMapping(value="/count")
	public int count(@RequestBody ReplyVO vo) {	
		log.info(vo);
		return service.count(vo.getBno());
	}*/
	
}
