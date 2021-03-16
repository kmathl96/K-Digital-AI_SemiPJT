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
	<div>
		<img src="" class="img-fluid float-start" alt="image" data-bs-toggle="modal" data-bs-target="#changeProfileImageModal">
		<!-- Modal -->
		<div class="modal fade" id="changeProfileImageModal" tabindex="-1" aria-labelledby="changeProfileImageModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="changeProfileImageModalLabel">프로필 사진 변경</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		      	<form action="/semipjt/changeProfileImage" method="post" enctype="multipart/form-data">
		      		<input type="file" name="uploadFile" />
			        <button type="button" class="btn btn-primary">Save change</button>
		      	</form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		<div class="d-flex flex-column bd-highlight mb-3">
		  <div class="p-2 bd-highlight"><h3>name</h3></div>
		  <div class="p-2 bd-highlight"><a>비밀번호 변경</a></div>
		  <div class="p-2 bd-highlight"><a>회원 탈퇴</a></div>
		</div>
	</div>
	<ul class="nav nav-tabs">
	  <li class="nav-item">
	    <a class="nav-link active" aria-current="page" href="#">내 질문</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link" href="#">내 댓글</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link" href="#">스크랩한 질문</a>
	  </li>
	</ul>
</article>
