<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--헤더 파일 넣기 --%>    
<%@ include file="../includes/header.jsp" %>
    
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">등록 게시판</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            게시글 쓰기
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <form action="register" method="post" role="form">
                                        <div class="form-group">
                                            <label>제목</label>
                                            <input class="form-control" name="title" >
                                        </div>    
                          				 <div class="form-group">
                                            <label>작성자</label>
                                            <input class="form-control" name="writer">
                                        </div>   
                                        <div class="form-group">
                                            <label>내용</label>
                                            <textarea class="form-control" rows="3" name="content"></textarea>
                                        </div>   
                                        <button type="submit" class="btn btn-default">Submit Button</button>
                                        <button type="reset" class="btn btn-default">Reset Button</button>
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

    <!-- /#wrapper -->

<%--footer 파일 넣기 --%>    
<%@ include file="../includes/footer.jsp" %>
