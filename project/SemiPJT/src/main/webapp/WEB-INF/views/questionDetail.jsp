<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
	<h1 class="text-center fw-bold m-3">
		${que.title }
	</h1>
	
<article class="p-5 m-3 bg-light border border-dark border-5">
	<h5>${que.w_name }</h5>
	<p class="text-secondary text-end">${que.created_at }</p>
	<img src="" class="img-fluid" alt="image">
	<p>${que.content }</p>
	<c:if test="${request_user.id==que.w_id }">
		<div class="d-flex justify-content-center bd-highlight mb-3 m-3">
		  <div class="p-2 bd-highlight"><a href="" class="btn btn-warning"><b>Edit</b></a></div>
		  <div class="p-2 bd-highlight"><a href="" class="btn btn-danger text-dark" data-bs-toggle="tooltip" data-bs-placement="right" title="다른 사람들에게도 도움이 되도록 지우지 말아주세요!"><b>Delete</b></a></div>
		</div>
	</c:if>
	<hr>
	<jsp:include page="commentList.jsp"/>
</article>
<h1 class="text-center"><i class="fas fa-angle-double-down"></i></h1>
<article class="p-5 m-3 bg-light border border-dark border-5">
	<!-- <h1 class="text-center fw-bold">
		Answer
	</h1> -->
	<div class="d-grid gap-2 d-md-flex justify-content-md-end">
	  <a href="" class="btn btn-warning"><b>Edit</b></a>
	  <a href="" class="btn btn-danger text-dark"><b>Delete</b></a>
	</div>
	<img src="" class="img-fluid" alt="image">
	<p>It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
</article>
