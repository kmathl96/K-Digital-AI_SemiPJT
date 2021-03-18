<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<h1 class="text-center fw-bold m-3">
	My page
</h1>
<!-- <div class="m-3 pe-3 d-grid gap-2 d-md-flex justify-content-md-end">
  <a href="" class="btn btn-warning"><b>Create!</b></a>
</div> -->
<article class="p-5 m-3 bg-light border border-dark border-5">
	<div class="p-5 mx-5">
		<div>
			<img src="resources/profileUpload/${request_user.profile_img }" class="img-fluid float-start me-3 rounded-circle" alt="image" id="mypage_profileImg">
			<div class="d-flex flex-column bd-highlight mb-3">
			  <div class="p-2 bd-highlight"><h3><b>${request_user.name }<c:if test="${request_user.account_type=='teacher' }">선생님</c:if></b></h3></div>
			  <div class="p-2 bd-highlight text-secondary">${request_user.id }</div>
			  <div class="p-2 bd-highlight">
			  	<a href="./changeProfile" class=" btn btn-warning text-reset text-decoration-none"><b>회원 정보 변경</b></a>
			  	<a class="btn btn-danger text-dark"><b>탈퇴하기...</b></a>
		  	  </div>
			</div>
		</div>
		<ul class="nav nav-tabs">
		  <li class="nav-item">
		    <a class="nav-link active in text-reset" aria-current="page" href="#question" data-toggle="tab" data-load="true">내 질문</a>
		  </li>
		  <li class="nav-item">
		    <a class="nav-link text-reset" href="#comment"  data-toggle="tab" data-load="false">내 댓글</a>
		  </li>
		  <li class="nav-item">
		    <a class="nav-link text-reset" href="#scrap" data-toggle="tab" data-load="false">스크랩한 질문</a>
		  </li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane fade in active" id="question">
				<jsp:include page="myQuestions.jsp"/>
			</div>
			<div class="tab-pane fade" id="comment">
				<jsp:include page="myComments.jsp"/>
			</div>
			<div class="tab-pane fade" id="scrap">
				<jsp:include page="myScraps.jsp"/>
			</div>
		</div>
	</div>
</article>