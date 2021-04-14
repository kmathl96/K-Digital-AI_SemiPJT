<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1 class="text-center fw-bold m-3">
	Edit My Profile
</h1>
<article class="p-5 m-3 bg-light border border-dark border-5">
	<form action="/changeProfileImage" method="post" enctype="multipart/form-data">
	  <div class="mb-3">
	    <label for="changeInputImg" class="form-label"><b>프로필 사진</b></label><br>
		<input type="file" name="uploadFile" id="changeInputImg" value="${request_id.profile_img }"/>
	  </div>
	  <div class="mb-3">
	    <label for="changeInputName" class="form-label"><b>이름</b></label>
	    <input type="text" name="name" class="form-control" id="changeInputName" value="${request_user.name }">
	  </div>
	  <div class="mb-3">
	    <label for="changeInputPassword1" class="form-label"><b>변경할 비밀번호</b></label>
	    <input type="password" name="password" class="form-control" id="changeInputPassword1">
	  </div>
	  <div class="mb-3">
	    <label for="changeInputPassword2" class="form-label"><b>비밀번호를 다시 한 번 입력해주세요!</b></label>
	    <input type="password" name="confirmPassword" class="form-control" id="changeInputPassword2">
	  </div>
	  <button type="submit" class="btn btn-primary">Submit</button>
	</form>
</article>
