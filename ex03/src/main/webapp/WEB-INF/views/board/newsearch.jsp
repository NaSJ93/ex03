<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <script src="http://code.jquery.com/jquery-3.7.1.min.js"></script>
<form>
	페이지 번호 : <input type="number" id="pageNum" ></br>
	보여줄 개수 : <input type="number" id="amount" ></br>	
	검색할 종류 : <select id="type">				
		<option value="T" >제목</option>
		<option value="C" >내용</option>
		<option value="W" >작성자</option>
		<option value="TC" >제목 or 내용</option>
		<option value="TW" >제목 or 작성자</option>
		<option value="CW" >내용 or 작성자</option>
		<option value="TCW" >제목 or 내용 or 작성자</option>	
	</select>		</br>
	검색할 내용 : <input type="text" id="keyword" ></br>
	<button type="button" id="search">검색</button> 
</form>
<p id="ss"></p>
</body>

<script>
$(document).ready(function(){	
	console.log("dsdd");
	$("#search").click(function(){
		console.log("이건 됐냐?");
		var pageNum=$("#pageNum").val();
		var amount=$("#amount").val();
		var type=$("#type").val();
		var keyword=$("#keyword").val();
		var dd={type:type,keyword:keyword};		
		console.log(pageNum);
		console.log(amount);
		console.log(type);
		console.log(keyword);
		console.log(dd);
		$.ajax({
			type:"post",
			url:"/myapi/board/"+pageNum+"/"+amount,
			contentType:"application/json; charset=utf-8",
			data:JSON.stringify(dd),
			dataType:"JSON",
			success:function(result){
					console.log(result);
					var aa=JSON.stringify(result)
					$("#ss").text(aa);
				},
			error:function(xhr,status,er){	
				if(error)
					error(er);
			}		
		})
	});
});	
</script>
</html>
