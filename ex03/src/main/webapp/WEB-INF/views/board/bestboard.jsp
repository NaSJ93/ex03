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
$(function(){
	$("#ss").on("click",function(){
		setInterval(function(){
		$.ajax({
			type:"get",
			url:"/myapi/best",
			//contentType:"application/json; charset=utf-8",
			dataType:"JSON",
			success:function(result){				
				var htmlStr="";
				for(var i=0;i<3;i++){
					htmlStr+=(i+1)+"등. 글번호:"+result[i].bno+", 제목:"+result[i].title+", 댓글 수:("+result[i].count+"+"+")<br>"	
				}
				//var aa=JSON.stringify(result)
				$("#list").html(htmlStr);
			}
		})	
		},3000);
	});
});
</script>
</html>