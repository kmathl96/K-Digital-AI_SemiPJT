<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<h1 class="text-center fw-bold m-3">
	Questions
</h1>
<div class="m-3 pe-3 d-grid gap-2 d-md-flex justify-content-md-end">
  	<a href="./createQuestion" class="btn btn-warning"><b>New</b></a>
</div>
<article class="p-5 m-3 bg-light border border-dark border-5">
	<table class="table table-hover">
	  <thead>
	    <tr>
	      <th scope="col">No</th>
	      <th scope="col">Title</th>
	      <th scope="col">Student</th>
	      <th scope="col">Date</th>
	    </tr>
	  </thead>
	  <tbody>
	  	<c:forEach var="question" items="${questions }" varStatus="status">
		    <tr>
		      <th scope="row">${status.count} </th>
		      <td>${question.title }</td>
		      <td>${question.w_id }</td>
		      <td>${question.created_at }</td>
		    </tr>
	  	</c:forEach>
	  </tbody>
	</table>
</article>
