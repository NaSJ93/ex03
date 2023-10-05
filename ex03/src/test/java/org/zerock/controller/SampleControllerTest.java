package org.zerock.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.zerock.domain.ReplyVO;
import org.zerock.domain.SampleVO;

import com.google.gson.Gson;

import lombok.extern.log4j.Log4j;

@WebAppConfiguration //컨트롤러 테스트 위해 추가
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"	//컨트롤러 테스트 위해 추가	
})	//컨트롤러는 웹서버에서 돌아가므로 servlet에도 추가를 해야함
@Log4j
public class SampleControllerTest {
	
	@Autowired	//mock과 달리 자동 저장이 가능
	private WebApplicationContext ctx;	//mockMvc 객체를 만들때 필요
	
	private MockMvc mockMvc;	//이 객체를 이용해서 컨트롤러 테스트 가능함(굳이 서버를 열어서 하지 않고)
								//또한 객체를 직접 인스턴(생성)해서 사용해야함
	@Before	//junit꺼 골라야함 / junit으로 테스트 할때 먼저 수행하는 메소드	//mockMvc는 처음에 null값이라서
	public void setup() {
		mockMvc=MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testTicket() throws Exception {												
		//넣어주기 위한 json data
		SampleVO vo =new SampleVO(2, "abc", "def");
		Gson gson=new Gson();	//json 형태 직접 안만들고 Gson 이용해서 해결하는 법
		String jsonData=gson.toJson(vo);
		log.info("json 형태로 변환"+jsonData);
		
		mockMvc.perform(post("/sample/ticket")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonData)).andExpect(status().is(200));
	}
	//crud = create, read, update,delete
	//method=get,post,put,delete		~370p
	
	//json 만들기
	@Test
	public void makeJson() {
		ReplyVO vo=new ReplyVO();
		vo.setBno(996L);
		vo.setReply("json테스트");
		vo.setReplyer("json테스터");
		log.info("json문자열:"+new Gson().toJson(vo));
	}
	
}
