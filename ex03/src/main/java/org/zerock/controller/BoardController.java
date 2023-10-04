package org.zerock.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/**")
@AllArgsConstructor
public class BoardController {		//211p
		
	BoardService service;
		
	// 전체목록 board/list (get)
	@GetMapping("/list")	
	public void list(Model model,Criteria cri) {
		log.info("list url 요청");
		model.addAttribute("list",service.getList(cri));	//글목록
		model.addAttribute("pageMaker", new PageDTO(cri, service.count(cri))); //페이지바 정보
		model.addAttribute("count", service.count(cri));		
		model.addAttribute("start", new Criteria());
		log.info(service.date());
		model.addAttribute("date", service.date());
		//-->board.list.jsp
	}
	
	//등록처리(작성글-BoardVO) /board/register (post) 	<-	입력화면(get)
	@PostMapping("/register")
	public String register(BoardVO vo,RedirectAttributes rttr) {
		log.info("register url post 요청");
		service.register(vo);
		rttr.addFlashAttribute("oper", "create");	//list.jsp 맨 아래 script에 빈값 처리 때문에 추가
		rttr.addFlashAttribute("result", vo.getBno()); //why? 데이터(작성글번호) 한번만 전송
		return "redirect:/board/list";	//why? redirect (새로고침으로 도배 방지) 
	}									//redirect가 없으면 jsp이고, 있으면 요청
	
	@GetMapping("/good")
	public String good(Long bno,Criteria cri) {
		log.info("좋아요 입력 요청 url");
		service.insertGood(bno);
		return "redirect:/board/get?bno="+bno+"&pageNum="+cri.getPageNum()+"&amount="+cri.getAmount();
	}
	
	@GetMapping("/register")		
	public void regi() {
		
	}	
	
	//	@GetMapping({"/register","/remove"})		거듭된 기능 추가로 뭔가 많이 바뀜
	@GetMapping("/remove")	//비번 화면 요청
	public void remove(Long bno,Model model) {
		model.addAttribute("bno", bno);
	}
	
	@GetMapping("/modify")
	public void modify(Long bno,Model model,Criteria cri) {
		model.addAttribute("board", service.get(bno));
		model.addAttribute("cri", cri);	//get페이지에서 보냄
	
	}
	
	//조회(글번호-bno) /board/get (get)
	@GetMapping("/get")
	public void get(Long bno,Model model,Criteria cri) {
		log.info("글 상세보기 url 요청");
		model.addAttribute("board", service.get(bno));
		model.addAttribute("cri", cri);	//list에서 보냄
		model.addAttribute("countG", service.countGood(bno));
		//	->	board/get.sjp
	}
	
	//삭제(글번호-bno) /board/remove (post)	<- 입력화면(get)
	@PostMapping("/remove")
	public String remove(Long bno,RedirectAttributes rttr,String pw) {
		log.info("삭제 url 요청");
		log.info("입력된 패스워드:"+ pw);
		if(pw.equals("1234")) { 
			if(service.remove(bno)) {	//이상 없으면 result란 이름으로 success라는 문자 전송
				rttr.addFlashAttribute("oper", "remove");
				rttr.addFlashAttribute("result", bno);
			}
			return "redirect:/board/list";
		}else {
			rttr.addFlashAttribute("flag", "fail");
			return "redirect:/board/remove?bno="+bno;
		}
	}
	/*
	@GetMapping("/remove")
	public void remove() {
		
	}
	*/
	
	//수정처리(수정글-BoardVO) /board/modify (post)	<- 입력화면(get)
	@PostMapping("/modify")
	public String modify(BoardVO vo,RedirectAttributes rttr,Criteria cri) {
		log.info("수정 url 요청");
		if(service.modify(vo)) {
			rttr.addFlashAttribute("oper", "modify");
			rttr.addFlashAttribute("result", vo.getBno());
		}
		return "redirect:/board/list?pageNum="+cri.getPageNum()+"&amount="+cri.getAmount();
	}
	/*
	@GetMapping("/modify")
	public void modify() {
		
	}
	*/
	
}
