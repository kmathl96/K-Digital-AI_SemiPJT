<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.2/css/all.css" integrity="sha384-vSIIfh2YWi9wW0r9iZe7RJPrKwp6bG+s9QZMoITbCckVJqGCCRhc+ccxNcdpHuYu" crossorigin="anonymous">
	<style>
		body {
			background-color: Cornsilk;
		}
		section {
			margin: 100px;
		}
		article {
			border-radius: 50px;
		}
	</style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Link</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Dropdown
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="#">Action</a></li>
            <li><a class="dropdown-item" href="#">Another action</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">Something else here</a></li>
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
        </li>
      </ul>
      <form class="d-flex mb-0">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-light" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>
<section>
	<h1 class="text-center fw-bold m-3">
		Title</i>
	</h1>
	<div class="d-flex bd-highlight mb-3 m-3">
	  <div class="me-auto p-2 bd-highlight"><h3><b>Writer name</b></h3></div>
	  <div class="p-2 bd-highlight"><a href="" class="btn btn-warning"><b>Edit</b></a></div>
	  <div class="p-2 bd-highlight"><a href="" class="btn btn-danger text-dark"><b>Delete</b></a></div>
	</div>
	
	<article class="p-5 m-3 bg-light border border-dark border-5">
		<img src="" class="img-fluid" alt="image">
		<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>
		<hr>
		<h4><b>Comments</b>  <i class="fas fa-comment-alt fa-sm"></i></h4>
		<table class="table table-borderless table-sm p-3">
		  <tbody>
		    <tr>
		      <td><b>user1</b></td>
		      <td>content1</td>
		      <td></td>
		    </tr>
		    <tr>
		      <td><b>user2</b></td>
		      <td>content2</td>
		      <td></td>
		    </tr>
		    <tr>
		      <td><b>user3</b></td>
		      <td>content3</td>
		      <td></td>
		    </tr>
		    <tr>
		      <td><b>reader</b></td>
		      <td><input type="text" class="form-control form-control-sm" id="title" placeholder="의견을 남겨보세요!"></td>
		      <td><button class="btn btn-warning btn-sm">Create!</button></td>
		    </tr>
		  </tbody>
		</table>
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
</section>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
</body>
</html>
