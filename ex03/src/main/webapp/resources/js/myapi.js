console.log("myapi 접속");
var apiService=(function(){
	
	function rpbnoCount(){
	console.log("글 댓글 번호api");
	$.ajax({
			type:"get",
			url:"/myapi/replybnocount",
			//data:JSON.stringify(reply),	 
			contentType:"application/json; charset=utf-8",
			success:function(result,status,xhr){
					if(callback)			
						callback(result);	
				},
			error:function(xhr,status,er){
				if(error)
					error(er);
			}		
		})
	}
	
	return {rpbnocount:rpbnocount};
})();