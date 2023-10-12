<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
실시간 순위  <br>
<div id="list"></div>
</body>

<!-- jQuery -->
<script src="/resources/vendor/jquery/jquery.min.js"></script>
<script>
var preResult;	//이걸 밖으로 빼는 이유 -> 안쪽에 넣으면 재시작 되면서 다시 초기화 됨
function bestbno(){		
		//var preResult;	
		$.ajax({
			type:"get",
			url:"/myapi/best",
			//contentType:"application/json; charset=utf-8",
			dataType:"JSON",
			success:function(result){			
				var htmlStr="";
				//이전 데이터와 현재 데이터를 비교해서 순위변경 표시
				if(preResult){	//최초에는 null라서 오류 생김 (preResult!=null) 이거와 같은 내용임 자바스크립트에선
					console.log("변경순위확인");
					console.log(preResult);
					for(var rank=0;rank<result.length;rank++){	//현재데이터 가지고 이전 데이터 비교
						for(var i=0;i<preResult.length;i++){	//각각의 현재값이 이전에 어디에 있는지 확인
							if(result[rank].title==preResult[i].title){
								var diffRank=0; //순위 유지 
								//찾았다.
								if(rank>i){ //순위 하락
									diffRank=i-rank; //(-) 값이면 하락 | (+) 값이면 상승
								}else if(rank<i){ //순위 상승
									diffRank="+"+(i-rank);
								}else{	//순위 유지
									//할거 없음 지워도 됨	
								}
								htmlStr += (rank+1)+"등글:"+result[rank].title+"("+diffRank+")<br>";
								break;
							}//if(result[rank]							
						}//for(var i=0;i<	
							if(i==preResult.length){	//break로 탈출한게 아님
								htmlStr += (rank+1)+"등글:"+result[rank].title+"(new)<br>";
							}			
					}//for(var rank=0;
										
				}else{//최초 로딩시
					for(var i=0; i<result.length; i++){
						htmlStr += (i+1)+"등글:"+result[i].title+"(-)<br>";
					}					
				}				
				$("#list").html(htmlStr);
				preResult=result; //이전데이터 보관 				

				//var htmlStr="";
				//for(var i=0;i<result.length;i++){
			//		htmlStr+=(i+1)+"등. 글번호:"+result[i].bno+", 제목:"+result[i].title+", 댓글 수:("+result[i].count+"+"+")<br>";	
			//}
				//var aa=JSON.stringify(result)				
			}	
		})//$.ajax({
}//function bestbno()	


bestbno(); //최초 바로 실행하고
setInterval(bestbno,3000); //3초후 새로고침

</script>
</html>