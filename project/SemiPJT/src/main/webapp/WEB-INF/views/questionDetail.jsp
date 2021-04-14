<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
	
<article class="p-5 m-3 bg-light border border-dark border-5">
	<div class="d-flex justify-content-between my-3">
		<h5><img src="/semipjt/resources/profileUpload/${writer.profile_img }" class="comment_profileImg img-fluid float-start me-3 rounded-circle" alt="image"}><b>${writer.name }</b> 님의 질문</h5>
		<p class="text-secondary text-end">${que.created_at }</p>
	</div>
	<h1 class="text-center fw-bold m-3">
		${que.title }
	</h1>
	<c:if test="${!empty que.img }">
		<img src="/semipjt/resources/questionUpload/${que.img }" class="img-fluid m-3" alt="image">
	</c:if>
	
	<p class="m-3">${que.content }</p>
	<div class="d-flex justify-content-center mb-2">
		<div class="btn btn-outline-dark p-4 p-3 position-relative">
			<i class="fas fa-thumbtack fa-2x pb-3"></i><br><a href="./${que.id}/scrapQuestion" class="stretched-link text-decoration-none text-reset">Scrap!</a>
		</div>
	</div>
	<c:if test="${request_user.id==que.w_id }">
		<div class="d-flex justify-content-center bd-highlight mb-3 m-3">
		  <div class="p-2 bd-highlight"><a href="" class="btn btn-warning"><b>Edit</b></a></div>
		  <div class="p-2 bd-highlight"><a href="" class="btn btn-danger text-dark" data-bs-toggle="tooltip" data-bs-placement="right" title="다른 사람들에게도 도움이 되도록 지우지 말아주세요!"><b>Delete</b></a></div>
		</div>
	</c:if>
	<hr>
	<jsp:include page="commentList.jsp"/>
</article>
<c:if test="${not empty ans || request_user.account_type!='student' }">
	<jsp:include page="answer.jsp"/>
</c:if>
<%-- <article class="p-5 m-3 bg-light border border-dark border-5">
	<!-- <h1 class="text-center fw-bold">
		Answer
	</h1> -->
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
 --%>