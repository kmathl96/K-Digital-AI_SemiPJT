<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1 class="text-center"><i class="fas fa-angle-double-down"></i></h1>
<article class="p-5 m-3 bg-light border border-dark border-5">
	<!-- 답변이 없을 경우 -->
	<c:choose>
	  	<c:when test="${empty ans }">
	  		<c:if test="${request_user.account_type=='teacher' }">
				<form action="./${que.id }/createAnswer" method="post">
					<div class="mb-3">
					  <label for="content" class="form-label"><b>Content</b></label>
					  <textarea name="answerContent" class="form-control" id="content" rows="3" placeholder="내용을 입력하세요."></textarea>
					</div>
					<div class="mb-3">
					  <label for="file" class="form-label"><b>Upload file</b></label>
					  <input name="answerFilename" class="form-control" type="file" id="file">
					</div>
					<button type="submit" class="btn btn-warning"><b>Create!</b></button>
				</form>
			</c:if>
		</c:when>
		<c:otherwise>
			<div class="d-flex justify-content-between mb-3">
				<h5><b>${ans.w_name }</b> 선생님의 답변</h5>
				<c:if test="${request_user.account_type=='teacher' }">
					<div class="d-grid gap-2 d-md-flex justify-content-md-end">
					  <a href="" class="btn btn-warning"><b>Edit</b></a>
					  <a href="" class="btn btn-danger text-dark"><b>Delete</b></a>
					</div>
				</c:if>
			</div>
			<c:if test="${!empty ans.img }">
				<img src="/semipjt/resources/answerUpload/${ans.img }" class="img-fluid m-3" alt="image">
			</c:if>
			<p class="m-3">${ans.content }</p>
		</c:otherwise>
	</c:choose>
</article>
