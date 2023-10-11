<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
오늘의 리플 개수 : <span id="count"></span> <br>
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
			url:"/replies/today.json",
			success:function(result){
					console.log(result);
					$('#count').text(result);
				}			
		})
		
		$.ajax({
			type:"get",
			url:"/replies/todaylist",
			//contentType:"application/json; charset=utf-8",
			dataType:"JSON",
			success:function(result){
				console.log(result);
				var htmlStr="";
				for(var i=0;i<result.length;i++){
					htmlStr+=(i+1)+". "+result[i].reply+"("+result[i].replyer+")<br>"	
					console.log("뭐임",htmlStr);
				}
				//var aa=JSON.stringify(result)
				console.log("됐냐",htmlStr);
				$("#list").html(htmlStr);
			}
		})	
		},1000);
	});
});
</script>
</html>