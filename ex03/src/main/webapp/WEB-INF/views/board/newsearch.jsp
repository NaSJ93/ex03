<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	페이지 번호 : <input type="number" id="pageNum" ><br>
	보여줄 개수 : <input type="number" id="amount" ><br>	
	검색할 종류 : <select id="type">				
		<option value="T" >제목</option>
		<option value="C" >내용</option>
		<option value="W" >작성자</option>
		<option value="TC" >제목 or 내용</option>
		<option value="TW" >제목 or 작성자</option>
		<option value="CW" >내용 or 작성자</option>
		<option value="TCW" >제목 or 내용 or 작성자</option>	
	</select>	<br>
	검색할 내용 : <input type="text" id="keyword" ><br>
	<button type="button" id="search">검색</button> 
<div></div>
</body>
<!-- jQuery -->
<script src="/resources/vendor/jquery/jquery.min.js"></script>
<script>
//1.버튼이 클릭되었을때 필요한 내용 실행
//2.사용자가 입력한 값들을 읽어오기
//3.그 값들을 이용해서 필요한 api 요청
//4.응답받은 데이터를 이용해서 필요한 화면부분을 갱신
$(function(){	
	console.log("dsdd");	//on("click",function(e){ });
	$("#search").click(function(){
		console.log("이건 됐냐?");
		var pageNum=$("#pageNum").val();
		var amount=$("#amount").val();
		var type=$("#type").val();
		var keyword=$("#keyword").val();
		var dd={type:type,keyword:keyword};		
		console.log(pageNum,amount,type,keyword);
		console.log(dd);
		$.ajax({
			type:"post",
			url:"/myapi/board/"+pageNum+"/"+amount,
			contentType:"application/json; charset=utf-8",
			data:JSON.stringify(dd),	//'{"type":"'+type+'","keyword":"'+keyword+'"}'  JSON.stringify(dd)
			dataType:"JSON",
			success:function(result){
					console.log(result);
					var htmlStr="";
					for(var i=0;i<result.length;i++){
						htmlStr+=(i+1)+". "+result[i].title+"<br>"	
						console.log("뭐임",htmlStr);
					}
					//var aa=JSON.stringify(result)
					console.log("됐냐",htmlStr);
					$("div").html(htmlStr);
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
