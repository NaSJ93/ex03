<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
오늘의 베스트 글  <br>
<button id="ss">확인</button>
<div id="list"></div>
</body>

<!-- jQuery -->
<script src="/resources/vendor/jquery/jquery.min.js"></script>
<script>
var preResult;	//이걸 밖으로 빼는 이유 -> 안쪽에 넣으면 재시작 되면서 다시 초기화 됨
$(function(){	
	$("#ss").on("click",function(){
		setInterval(function(){
		//var preResult;	
		$.ajax({
			type:"get",
			url:"/myapi/best",
			//contentType:"application/json; charset=utf-8",
			dataType:"JSON",
			success:function(result){			
				//이전 데이터와 현재 데이터를 비교해서 순위변경 표시
				if(preResult){	//최초에는 null라서 오류 생김 (preResult!=null) 이거와 같은 내용임 자바스크립트에선
					console.log("변경순위확인");
					for(var rank=0;rank<preResult.length;rank++){
						for(var i=0;i<3;i++){
							if(preResult[0].title==result[i].title){
								break;
							}
						}
						if(rank==i){ //순위 유지
							console.log(rank+1,"순위유지");
						}else if(i<4){
							console.log(rank+1,"순위변경");
						}					
					}	
				}
				var htmlStr="";
				for(var i=0;i<3;i++){
					htmlStr+=(i+1)+"등. 글번호:"+result[i].bno+", 제목:"+result[i].title+", 댓글 수:("+result[i].count+"+"+")<br>"	
				}
				//var aa=JSON.stringify(result)
				$("#list").html(htmlStr);
				preResult=result; //이전데이터 보관
			}
		})	
		},3000);
	});
});
</script>
</html>