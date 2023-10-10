<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--헤더 파일 넣기 --%>    
<%@ include file="../includes/header.jsp" %>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">조회 게시판</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            게시글 쓰기 <small class="pull-right text-muted">(좋아요 : ${countG})</small>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                        <div class="form-group">
                                            <label>제목</label>
                                            <input class="form-control" placeholder="제목 입력" name="title" value="${board.title}" readonly>
                                        </div>
                                        <div class="form-group">
                                            <label>작성자</label>
                                            <input class="form-control" placeholder="작성자 입력" name="writer" value="${board.writer}" readonly>
                                        </div>                                        
                                        <div class="form-group">
                                            <label>Text area</label>
                                            <textarea class="form-control" rows="5" placeholder="내용 입력" name="content" readonly >${board.content}</textarea>
                                        </div>							<%--getmappin get에서 cri 보냄 --%>
                                        <a href="/board/modify?bno=${board.bno}&pageNum=${cri.pageNum}&amount=${cri.amount}"><button type="button" class="btn btn-warning">수정으로</button></a> 
                                        <a href="/board/list?pageNum=${cri.pageNum}&amount=${cri.amount}"><button type="button" class="btn btn-primary">목록으로</button></a>
                                        <a href="good?bno=${board.bno}&pageNum=${cri.pageNum}&amount=${cri.amount}" id="ddd"><button type="button" class="btn btn-success" onclick="javascript:alert('좋아요 완료'); this.onclick=null;" id="countA">좋아요</button></a>                                        
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                            </div>
                            <!-- /.row (nested) -->                            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                    	<!-- 댓글목록 -->
                          <div class='row'>
	                          <div class="col-lg-12">
	                          	<div class="panel panel-default"> 
	                          	 <div class="panel-heading">
	                          		<i class="fa fa-comments fa-fw"></i> 댓글 <span id="aaa"></span>
	                          	 </div>	
	                          		<div class="panel-body">
			                            <div>
			                            <!-- 댓글 쓰기 -->			                            	
			                            	<input type="text" placeholder="작성자" id="replyWriter"></br>
			                            	<input type="text" placeholder="댓글내용" id="replyData">
			                            	<button type="button" class="btn btn-info btn-circle" id="btnReplyWrite"><i class="fa fa-check"></i></button>
				                            <ol id="chat">				                        
				                            </ol>
			                            </div>
			                         </div>                               
	                            </div>
	                          </div>  
                          </div>
                          <!-- /댓글목록 -->
                          
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    <!-- /#wrapper -->

  <%--footer 파일 넣기 --%>    
<%@ include file="../includes/footer.jsp" %>

<%--댓글 처리 자바 스크립트 --%>
<script src="/resources/js/reply.js"></script>
<script>
	//console.log(replyService);
	//reply={reply:"자바스크립트로 테스트",replyer:"테스트",bno:997}
	//b=34;
	//replyService.add(reply);										//callback 함수
	//replyService.getList(996,function(a){console.log(a)});
	//replyService.getDelete(b,console.log(b+" 삭제됨"));
	//replyService.getModi(18,reply);
	//replyService.getOne(11,function(a){console.log(a)});
</script>
<script>
//리플목록 가져오기 함수
		//문서로딩이 완료되면
function replyList(){
	//1.해당하는 게시글의 댓글 목록 가져오고
	replyService.getList(${board.bno},function(reply){
		//2.댓글 잘 가공해서 화면에 넣을 내용 만들기 (html)
		console.log(reply);
		replyStr="";
		for(var i=0; i<reply.length; i++){
		//	replyStr +="<li>"+reply[i].reply+"("+reply[i].replyer+") - "+reply[i].replydate+"</li>";												//id가 아닌 class인 이유 : 여러개라서
			replyStr +="<li>"+"<span hidden>"+reply[i].rno+"</span>"+"<b>"+
			reply[i].replyer+"</b> 님"+replyService.time(reply[i].replydate)+
			" [<span class=\"btnDel\"><a href=\"#\">X</a></span>]</br><i class=\"fa fa-caret-right fa-fw\"></i>"+
			'<span class="replyModify">'+reply[i].reply+"</span></li>";
		}																																			
		console.log("넣어야할 리플화면",replyStr);		
	//3.해당 위치에 넣어준다.
		$("#chat").html(replyStr);
		sum="(댓글 수 : "+reply.length+")"; 		
		$("#aaa").html(sum);
	});
}	

$(document).ready(function(){
	replyList();
});


$("#btnReplyWrite").on("click",function(e){
	//1.작성 데이터와 글번호를 읽어서
	var replyData=$("#replyData").val();
	var replyWriter=$("#replyWriter").val();
	var bno="${board.bno}";	//""를 하는 이유는 혹시 bno가 빈값이라면 오류 날수 있으므로 혹시 없더라도 빈값으로 가게함
	console.log("작성할 리플내용:",replyData,replyWriter,bno);
	//2.리플객체를 만들어서
	var reply={bno:bno,reply:replyData,replyer:replyWriter};
	//3.리플등록처리	//4.리플목록 갱신(콜백으로 넣어줘야함why?ajax는 비동기 처리 되기 때문)
	replyService.add(reply,replyList);
	//빈값처리하기
	$("#replyData").val(""); $("#replyWriter").val("");
	//4.리플목록갱신
	//replyList();
});

//!!!!!!424p!!!!!
/*
 * 동적으로 만들어진 DOM임으로 이벤트 리스너가 등록이 불가함으로 | 위임을 통해서 이벤트 등록시키자
$(".btnDel").on("click",function(e){
	console.log("삭제버튼 클릭");
});
*/
//#chat 존재 DOM        .btnDel실제대상(동작요소)
$("#chat").on("click",".btnDel",function(e){
	e.preventDefault();//걸려있는 이벤트 무시(하이퍼링크)
	console.log("삭제버튼 클릭");	
	var pw=prompt("패스워드를 입력하세요");	//javascript라서 소스로 보면 다 보임
	if(pw=="1234"){
		//1.해당하는 댓글번호 알기
		var rno=$(this).prev().prev().prev().text();
		console.log("댓글번호",rno);
		//2.삭제 js 호출 3.콜백으로 댓글 목록 갱신
		replyService.getDelete(rno,replyList);
	}else{
		alert("패스워드가 다릅니다.");
	}
});

//수정
//1.댓글내용이 클릭되면 수정이 가능하도록 입력창으로 변경
$("#chat").on("click",".replyModify",function(e){	
	console.log("댓글내용클릭");
	//클릭한 댓글 내용
	var replyData=$(this).text();	//클릭한 댓글 내용
	console.log("댓글내용:",replyData);
	var rno=$(this).prev().prev().prev().prev().prev().prev().text();	//클릭한 댓글 번호
	console.log("댓글번호",rno);		
	var str='<input type="text" value="'+replyData+'">';
	$(this).html(str).children().focus();	//바로 수정이 가능하도록 포커스 이동
	//2.포커스가 사라지면 수정이 되도록 js 호출
	//blur : 포커스가 떠날때
	$("#chat").on("blur","input",function(e){
		console.log("댓글수정작업");
		//변경한 댓글 내용(수정내용)
		var modiData=$(this).val();
		console.log("수정할내용:",modiData);
		var reply={reply:modiData};		//3.목록 갱신
		replyService.getModi(rno,reply,replyList);		
	});
	
});

</script>

<script>	
$("#countA").on("click",function(){
	//$(this).parents().attr("href","get?bno=${board.bno}&pageNum=${cri.pageNum}&amount=${cri.amount}");
});
</script>
