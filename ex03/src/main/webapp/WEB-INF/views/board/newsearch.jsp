<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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
</body>
</html>

<script>
$("#search").click(function(){
	var pageNum=$("#pageNum").text();
	var amount=$("#amount").text();
	var type=$("#type").text();
	var keyword=$("#keyword").text();
	console.log(pagenum,amount,type,keyword);
	$.ajax({
		type:"post",
		url:"/myapi/board"+pageNum+"/"+amount+".json",
		success:function(result,status,xhr){
				if(callback)			//callback이 있으면
					callback(result);	//callback에 result를 넣음
			},
		error:function(xhr,status,er){
			if(error)
				error(er);
		}		
	})
};
</script>