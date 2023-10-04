<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--헤더 파일 넣기 --%>    
<%@ include file="../includes/header.jsp" %>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">게시판</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            게시글 삭제확인 <a href="good?bno=${board.bno}"><button type="button">좋아요+1</button></a>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                  <form action="remove"  method="post">
                                  <input type="hidden" name="bno" value="${bno}">
                                  삭제 비밀번호:<input type="password" name="pw"> 
                                  <input type="submit" value="전송"> 
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

    </div>
    <!-- /#wrapper -->

<%--footer 파일 넣기 --%>    
<%@ include file="../includes/footer.jsp" %>
<script>
var flag="${flag}" //"":삭제요청 or "fail":패스워드 오류
if(flag=="fail"){
	alert("패스워드를 확인해주세요.");
}
</script>
</body>

</html>