<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">No</th>
      <th scope="col">Title</th>
      <th scope="col">Date</th>
      <th scope="col">Hits</th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach var="question" items="${questions }" varStatus="status">
	    <tr>
	      <th scope="row">${status.count} </th>
	      <td><div class="position-relative p-0"><a href="/semipjt/questionDetail/${question.id }" class="text-decoration-none text-reset stretched-link p-3">${question.title }</a></div></td>
	      <td>${question.created_at }</td>
	      <td>${question.hits }</td>
	    </tr>
  	</c:forEach>
  </tbody>
</table>