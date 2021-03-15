<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1 class="text-center fw-bold m-3">
	New Question
</h1>
<article class="p-5 m-3 bg-light border border-dark border-5">
	<form action="./createQuestion" method="post">
		<div class="mb-3">
		  <label for="title" class="form-label"><b>Title</b></label>
		  <input type="text" name="title" class="form-control" id="title" placeholder="제목을 입력하세요.">
		</div>
		<div class="mb-3">
		  <label for="content" class="form-label"><b>Content</b></label>
		  <textarea name="content" class="form-control" id="content" rows="3" placeholder="내용을 입력하세요."></textarea>
		</div>
		<div class="mb-3">
		  <label for="file" class="form-label"><b>Upload file</b></label>
		  <input name="filename" class="form-control" type="file" id="file">
		</div>
		<button type="submit" class="btn btn-warning"><b>Create!</b></button>
	</form>
</article>
