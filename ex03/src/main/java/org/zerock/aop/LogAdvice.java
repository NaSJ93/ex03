package org.zerock.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
@Component
public class LogAdvice {
	//*:모든값을 표현, ..:0개 이상
	//			(맨앞 *) :접근제한자
	//@Before("execution(* org.zerock.service.SampleService*.*(..))")	
	//'SampleService'의 모든것+ '*'(Impl 등도 포함)+ '.*'(의 모든 메소드)+'(..)'0개 이상
	//	@Before("execution(* org.zerock.service.BoardService*.getList(..))") ("execution(* org.zerock.controller.BoardController*.list(..))")	
	@Before("execution(* org.zerock.controller.Home*.*(..))")	
	public void list() {
		log.info("aop 홈 접속");
	}
	
	@Before("execution(* org.zerock.controller.BoardController*.list(..))")		
	public void modify() {
		log.info("aop 게시판 접속");
	}
	
	@Around("execution(* org.zerock.controller.BoardController*.register(..))")
	public Object register( ProceedingJoinPoint pjp ) {
		
		log.info("aop 등록 시작");
		
		Object result = null;
		 try {
			result = pjp.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 log.info("aop 등록 끝");
		 
		 return result;
	}
	
	/*
	@Before("execution(* org.zerock.")
	public void list() {
		log.info("");
	}
	*/
}
