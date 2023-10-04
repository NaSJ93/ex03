<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--헤더 파일 넣기 --%>    
<%@ include file="../includes/header.jsp" %>
    
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">수정 게시판</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            게시글 쓰기
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <form action="modify" method="post" role="form">
                                   		<input type="hidden" name="bno" value="${board.bno}">
                                   		<input type="hidden" name="pageNum" value="${cri.pageNum}"> <%--getmapping modify에서 보냄 --%>
                                   		<input type="hidden" name="amount" value="${cri.amount}">	
                                   		
                                        <div class="form-group">
                                            <label>제목</label>
                                            <input class="form-control" name="title" value="${board.title}"  id="title">
                                        </div>    
                          				 <div class="form-group">
                                            <label>작성자</label>
                                            <input class="form-control" name="writer" value="${board.writer}" readonly>
                                        </div>   
                                        <div class="form-group">
                                            <label>내용</label>
                                            <textarea class="form-control" rows="3" name="content" id="content">${board.content}</textarea>
                                        </div>   
                                        <button type="button" class="btn btn-default" data-oper="modify">수정하기</button>
                                        <button type="button" class="btn btn-warning" data-oper="remove">삭제하기</button> 
                                        <button type="button" class="btn btn-primary" data-oper="list">목록으로</button>
                                    </form>
                                </div>
                         
                                <!-- /.col-lg-6 (nested) -->
                            </div>
                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    <!-- /#wrapper -->

<%--footer 파일 넣기 --%>    
<%@ include file="../includes/footer.jsp" %>
    
    <script>	//262p
    $(function(){
    	var formObj=$("form");		//e=event (버튼이 클릭됏을때 나올 이벤트)
    	$("button").on("click",function(e){
    		e.preventDefault();	//버튼에 걸린 이벤트 없애고
    		var operation=$(this).data("oper");	//data-oper 속성의 값 읽기,즉 어떤 버튼인지 확인 (id로 해도 되긴함)
    		if(operation==="modify"){	
    			//formObj.submit();	//submit 전송
    			//1.원본 제목, 내용 읽기
				var title="${board.title}";
				console.log("원본제목:",title);
				var content=`${board.content}`;	//백틱 (1번 옆에 있는거)-여러줄 문자열을 받을 수 있음
				console.log("원본내용:",content);
    			//2.수정 제목, 내용 읽기
				var user_title=$("#title").val();
				console.log("수정제목:",user_title);
				var user_content=$("#content").val();
				console.log("수정내용:",user_content);
				if(title==user_title && content==user_content){	//수정안됨
					alert("수정하고 수정버튼 눌러라!!!");
					return;
				}
    		}
    		if(operation==="remove"){
    			formObj.attr("action","/board/remove");
    			formObj.attr("method","get");
    			//formObj.submit();	//submit 전송
    		}else if(operation==="list"){
    			self.location="/board/list";	//이건 post가 아닌 get이라서
    			return; //여기서 끝내기
    		}    		
    		formObj.submit();	//공통이라 밖으로 뻄
    	});
    	
    });
    </script>

