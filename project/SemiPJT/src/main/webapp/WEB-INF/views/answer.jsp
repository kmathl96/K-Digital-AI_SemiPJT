<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<article class="p-5 m-3 bg-light border border-dark border-5">
	<!-- 답변이 없을 경우 -->
	<c:choose>
	  	<c:when test="${empty ans }">
	  		<c:if test="${request_user.account_type=='teacher' }">
				<form action="./createAnswer" method="post">
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
				<div class="d-grid gap-2 d-md-flex justify-content-md-end">
				  <a href="" class="btn btn-warning"><b>Edit</b></a>
				  <a href="" class="btn btn-danger text-dark"><b>Delete</b></a>
				</div>
			</div>
			<img src="" class="img-fluid" alt="image">
			<p>${ans.content }</p>
		</c:otherwise>
	</c:choose>
</article>
