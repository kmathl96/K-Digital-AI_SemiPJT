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
	      <th scope="col">Hits</th>
	    </tr>
	  </thead>
	  <tbody>
	  	<c:forEach var="question" items="${questions }" varStatus="status">
		    <tr>
		      <th scope="row">${status.count} </th>
		      <td>
		      	<div class="position-relative p-0">
		      		<a href="./questionDetail/${question.id }" class="text-decoration-none text-reset stretched-link p-3">
		      			${question.title } <c:if test="${question.hits >= 50 }"> <span class="badge rounded-pill bg-danger">HIT</span> </c:if>
		      		</a>
	      		</div>
      		  </td>
		      <td>${question.w_name }</td>
		      <td>${question.created_at }</td>
		      <td>${question.hits }</td>
		    </tr>
	  	</c:forEach>
	  </tbody>
	</table>
</article>
