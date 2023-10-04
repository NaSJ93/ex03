package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Criteria {	//293p			+333p
	//페이지 처리 하기 위한 정보	+ 검색정보
	//(페이지 번호pageNum, 한 페이지에 보여줄 개수amount)
	
	//333p
	//검색선택(type) - C(내용)T(타이틀)W(작성어)
	//검색어(keyword)
	
	private int pageNum=1;	//기본값
	private int amount=10;
	
	private String type;
	private String keyword;
	
	public Criteria() {	//기본 생성자가 있어야 이걸로 객체 생성이 가능함
		
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	
	//type 글자를 한글자씩 자르는 메소드
	public String[] getTypeArr() {	//typeArr의 게터	#{typeArr}쓰면 getter가 호출됨
		if(type!=null)
			return type.split("");	//빈값 -> 한 글자씩 자름
		else
			return new String[] {};	//null이면 빈값 돌려주기 (이렇게 if 안쓰면  null값 자르는 경우 생겨서 오류남)		
	//	return type!=null ? type.split("") : new String[] {}; //(한줄로하기)
	}	
	
}
