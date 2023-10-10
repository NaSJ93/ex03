package org.zerock.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.BnoVO;
import org.zerock.domain.Total;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@AllArgsConstructor
public class ApiController {
	
	private BoardService service;
	
	@GetMapping(value = "/myapi/replybnocount", produces = MediaType.APPLICATION_JSON_VALUE)
	public Long replyCount() {
		log.info("REST api 접속");		
		return service.countRp();
	}
	//Json 형태로 데이터 보내기
	//1.
	//@PutMapping(value = "/myapi/bestbno", produces = MediaType.APPLICATION_JSON_VALUE)
	//public String bnoCount() {
	//	return "{\"bno\":"+service.bnoCount()+"}";
	//}
	
	//2.
	@PutMapping(value = "/myapi/bestbno", produces = MediaType.APPLICATION_JSON_VALUE)
	public BnoVO bnoCount() {
		BnoVO bnoVO=new BnoVO();		
		bnoVO.setBno(service.bnoCount());
		return bnoVO;
	}
	//<Total><count>55</count></Total>    | 	{"count":55}
	//<해당 클래스><해당 변수>
	@GetMapping(value="/myapi/{num1}/{num2}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE,})
	public Total sum(@PathVariable("num1") Long num1,@PathVariable("num2") Long num2) {
		Total a=new Total();
		Long sum=0L;
		for(Long i=num1;i<=num2;i++) {
			sum+=i;
		}
		a.setCount(sum);
		return a;
		
	}
}
