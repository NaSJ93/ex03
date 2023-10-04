package org.zerock.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {	//306p
	//화면에서 페이지바를 표시하기 위해 필요한 정보
	private int startPage,endPage;
	private boolean prev,next;
	
	private Criteria cri;	//현재 페이지,페이지당 글 개수
	private long total; //전체 글 개수
	private int end;
	
	public PageDTO(Criteria cri,long total) {
		this.cri=cri; this.total=total;
		
		//페이지 바의 끝페이지=((현재페이지/10).올림)*10	//(10.0으로 나눠서 실수로 만들기)
		endPage=(int)((Math.ceil(cri.getPageNum()/10.0))*10);
		//페이지 바의 시작 페이지 = 끝페이지-9
		startPage=endPage-9;
			
		//진짜 마지막 페이지=소수점 올림(전체글개수/한페이지당개수)	//(1.0 곱해서 실수로 만들기)
		int realEnd=(int)(Math.ceil((total*1.0)/cri.getAmount()));	//올림이지만 double로 리턴되므로
		end=realEnd;
		//페이지바의 끝페이지가 진짜마지막페이지보다 크면 진짜로 변경
		if(endPage>realEnd)
			endPage=realEnd;

		//endPage가 끝페이지가 아니라면 next가 true
		next=endPage!=realEnd;
		
		//시작 페이지가 1이 아닐때 prev는 true;
		prev=startPage !=1;
	
	}
	 
}
