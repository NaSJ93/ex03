package org.zerock.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;

import lombok.extern.log4j.Log4j;

@RestController	//data로 응답하겠다.
@RequestMapping("/sample")
@Log4j
public class SampleController {
											//plain순수한 텍스트를 보냄(이 작업들을 안해놓으면 한글로 보낼시 깨짐) ~359p
	@GetMapping(value = "/getText",produces="text/plain;charset=UTF-8")
	public String getText() {
		log.info("MIME TYPE:"+MediaType.TEXT_PLAIN_VALUE);		
		return "안녕하세요";	//@controller와 다르게 jsp가 출력되는게 아님
	}
									//json만 하면 json으로만 처리됨, //둘다 적으면 xml 우선, .json 쓰면 json	~361p) 
	@GetMapping(value = "/getSample",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public SampleVO getSample() {
	 return new SampleVO(1,"오늘은","수요일");			
		
		//json 변환 라이브러리만 있으면 json으로
		//xml 변환 라이브러리만 있으면 xml로
		//둘 다 있으면 xml이 우선
		//url뒤에 .json으로 하면 json / .xml은 ~~~
	}
	
	@GetMapping("/getList")
	public List<SampleVO> getList(){
	/*	List<SampleVO> list=new ArrayList<SampleVO>();				~363p		
		for(int i=0; i<10;i++) {
			SampleVO vo=new SampleVO(i, "성"+i, "이름"+i);
			list.add(vo);
		}
		return list;
		*/
						//0~9			중간단계						결과값을		저장		리스트에
		return IntStream.range(0, 10).mapToObj(i->new SampleVO(i, "성"+i, "이름"+i)).collect(Collectors.toList());		
	}
	
	@GetMapping("getMap")
	public Map<String, SampleVO> getMap(){
		Map<String, SampleVO> map=new HashMap<String, SampleVO>();
		map.put("나는키값", new SampleVO(1,"오늘은","수요일"));		
		
		return map;
	}
	
	//params특정키값이 존재하게 제한	(/sample/check?height=100&weight=50)
	//(파라미터 없으면 에러 처리)
	@GetMapping(value = "/check",params = {"height","weight"} )	//~365p 결과는 잘 출력됨 but 404로 나옴
	public ResponseEntity<SampleVO> check(int height, int weight){
		//SampleVO vo=new SampleVO(1,"오늘은","수요일");
		//return ResponseEntity.status(HttpStatus.NOT_FOUND).body(vo);
		
		ResponseEntity<SampleVO> result; 
		
		SampleVO vo=new SampleVO(1,height+"오늘은",weight+"수요일");
		if(height<150)
			result= ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		else
			result= ResponseEntity.status(HttpStatus.OK).body(vo);
		return result;
	}
	
	//366p url에 있는 값 읽기
	
	// /sample/product/원하는글자/원하는숫자
	// /sample/product/cat/123	
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(@PathVariable("cat") String cat, 
							@PathVariable("pid") Integer pid) {
		return new String[] {"cat값:"+cat,"pid값:"+pid};
	}
	
	//보낸 데이터를 읽는법	367p	확장 프로그램 Yey another REST Client	{"mno":123,"firstName":"가나다","lastName":"마바사"}
	@PostMapping("/ticket")
	public SampleVO convert(@RequestBody SampleVO vo) {
		log.info("수집이 잘되나?"+vo);
		return vo;
		
	}
}
