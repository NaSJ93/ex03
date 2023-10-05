console.log("댓글 처리하는 자바스크립트 작동");
var replyService=(function(){
	//댓글등록
	function add(reply,callback,error){
		console.log("댓글 등록........");
		$.ajax({
			type:"post",
			url:"/replies/new",
			data:JSON.stringify(reply),	//reply 데이터를 json 형태로 보내기 
			contentType:"application/json; charset=utf-8",
			success:function(result,status,xhr){
				console.log("댓글등록 정상처리");
					if(callback)			//callback이 있으면
						callback(result);	//callback에 result를 넣음
				},
			error:function(xhr,status,er){
				if(error)
					error(er);
			}		
		})
	}
	
	//댓글목록
	function getList(bno,callback,error){
		console.log("댓글 목록........");
	/*	$.ajax({
			type:"get",
			url:"/replies/pages/"+bno+".json",
			//data:JSON.stringify(reply),	//reply 데이터를 json 형태로 보내기 
			//contentType:"application/json; charset=utf-8",
			success:function(result,status,xhr){
				console.log("댓글목록 가져오기 정상처리");
					if(callback)			//callback이 있으면
						callback(result);	//callback에 result를 넣음
				},
			error:function(xhr,status,er){
				if(error)
					error(er);
			}		
		}) */
		//$.getJSON(요청주소, 성공시 처리함수).fail(실패시 처리함수);
		$.getJSON("/replies/pages/"+bno+".json",
					function(result){
						console.log("댓글목록 가져오기 정상처리");
							if(callback)			
								callback(result);	
					}).fail(function(xhr,status,er){
						if(error)
							error(er);
						});
	}	
	//댓글삭제
	function getDelete(rno,callback,error){
		console.log("댓글 삭제........");
		$.ajax({
			type:"delete",
			url:"/replies/"+rno,
			success:function(result,status,xhr){
				console.log("댓글삭제 정상처리");
					if(callback)			//callback이 있으면
						callback(result);	//callback에 result를 넣음
				},
			error:function(xhr,status,er){
				if(error)
					error(er);
			}		
		})
	}
	
	//댓글 수정
	function getModi(rno,reply2,callback,error){
		console.log("댓글 수정........");
		$.ajax({
			type:"put",
			url:"/replies/"+rno+".json",
			data:JSON.stringify(reply2),	//reply 데이터를 json 형태로 보내기 
			contentType:"application/json; charset=utf-8",
			success:function(result,status,xhr){
				console.log("댓글수정 정상처리");
					if(callback)			//callback이 있으면
						callback(result);	//callback에 result를 넣음
				},
			error:function(xhr,status,er){
				if(error)
					error(er);
			}		
		})
	}
	
	//댓글1개 가져오기
	function getOne(rno,callback,error){
		console.log("댓글 한개........");
		$.ajax({
			type:"get",
			url:"/replies/"+rno+".json",
			success:function(result,status,xhr){
				console.log("댓글한개 가져오기 정상처리");
					if(callback)			//callback이 있으면
						callback(result);	//callback에 result를 넣음
				},
			error:function(xhr,status,er){
				if(error)
					error(er);
			}		
		})
	}	
	
	//		이름: 값(add함수)
	return {add:add, getList:getList, getDelete:getDelete, getModi:getModi, getOne:getOne};
})();