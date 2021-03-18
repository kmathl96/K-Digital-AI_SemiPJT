<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h4><b>Comments</b>  <i class="fas fa-comment-alt fa-sm"></i></h4>
<div class="container">
  <c:choose>
  	<c:when test="${empty comments }">
  		<p class="text-center mt-5"><small>아직 댓글이 없어요!</small></p>
  		<p class="text-center mb-5"><small>댓글을 남겨주세요 :)</small></p>
  	</c:when>
  	<c:otherwise>
	  <c:forEach var="comment" items="${comments }">
		  <div class="row p-1">
		  	<div class="col-1"><img src="/semipjt/resources/profileUpload/${comment.w_img }" class="comment_profileImg img-fluid float-start me-3 rounded-circle" alt="image"></div>
		    <div class="col-2">${comment.w_name }</div>
		    <div class="col-9">${comment.content }</div>
		  </div>
	  </c:forEach>
  	</c:otherwise>
  </c:choose>
  <form action="/semipjt/questionDetail/${que.id }/createComment" method="post">
  	<div class="row mt-3">
  		<div class="col-1"><img src="/semipjt/resources/profileUpload/${request_user.profile_img }" class="comment_profileImg img-fluid float-start me-3 rounded-circle" alt="image"}></div>
		<div class="col-2"><b>${request_user.name }</b></div>
		<div class="col-8"><input name="content" type="text" class="form-control form-control-sm" id="title" placeholder="의견을 남겨보세요!"></div>
	    <button class="btn btn-warning btn-sm col-1">Create!</button>
  	</div>
  </form>
</div>