<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
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
		.card {
			border-radius: 50px;
			/* width: 25vw; */
			max-width: 30vw;
			margin: auto;
		}
		.card:hover {
			background-color: #F0F0F0;
			/* color: firebrick; */
		}
	</style>
</head>
<body>
<section>
	<h1 class="text-center fw-bold m-5">Nice meet you</h1>
	<!-- <h1 class="text-center fw-bold m-5"><i class="far fa-smile"></i></h1> -->
	<!-- <h1 class="text-center fw-bold m-5">Let's study together! <i class="fas fa-pencil-alt"></i></h1> -->
	<h1 class="text-center fw-bold m-5">Let's study together <i class="fas fa-fire-alt text-danger"></i></h1>
	<div class="p-5"></div>
	<!-- <div class="card-group m-2 bg-light border border-dark border-5">
	  <div class="card m-3 border-0 bg-light">
	  <i class="fas fa-sign-in-alt fa-5x"></i>
	    <img src="..." class="card-img-top" alt="...">
	    <div class="card-body">
	      <h5 class="card-title">Card title</h5>
	      <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
	      <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
	    </div>
	  </div>
	  <div class="card m-3 border-0 bg-light">
	  	<i class="fas fa-user-plus fa-5x"></i>
	    <img src="..." class="card-img-top" alt="...">
	    <div class="card-body">
	      <h5 class="card-title">Card title</h5>
	      <p class="card-text">This card has supporting text below as a natural lead-in to additional content.</p>
	      <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
	    </div>
	  </div>
	</div> -->
	
	<div class="row">
	  <div class="col-sm-6">
	    <div class="card p-5 me-1 border border-dark border-5"
	    data-bs-toggle="modal" data-bs-target="#loginModal">
	      <div class="card-body">
	      	<p class="text-center mb-5"><i class="fas fa-sign-in-alt fa-7x"></i></p>
	        <h2 class="card-title text-center fw-bold">Login</h2>
	        <!-- <p class="card-text">With supporting text below as a natural lead-in to additional content.</p> -->
	        <!-- <a href="#" class="btn btn-primary">Go somewhere</a> -->
	      </div>
	    </div>
	  </div>
	  <div class="col-sm-6">
	    <div class="card p-5 ms-1 border border-dark border-5"
	    data-bs-toggle="modal" data-bs-target="#signupModal">
	      <div class="card-body">
	      	<p class="text-center mb-5"><i class="fas fa-user-plus fa-7x"></i></p>
	        <h2 class="card-title text-center fw-bold mb-">Signup</h2>
	        <!-- <p class="card-text">With supporting text below as a natural lead-in to additional content.</p> -->
	        <!-- <a href="#" class="btn btn-primary">Go somewhere</a> -->
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- Login Modal -->
	<div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="loginModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="loginModalLabel">Login</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        <form>
			  <div class="mb-3">
			    <label for="exampleInputEmail1" class="form-label">Email address</label>
			    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
			    <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
			  </div>
			  <div class="mb-3">
			    <label for="exampleInputPassword1" class="form-label">Password</label>
			    <input type="password" class="form-control" id="exampleInputPassword1">
			  </div>
			  <button type="submit" class="btn btn-primary">Submit</button>
			</form>
	      </div>
	    </div>
	  </div>
	</div>
	<!-- Signup Modal -->
	<div class="modal fade" id="signupModal" tabindex="-1" aria-labelledby="signupModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="signupModalLabel">Signup</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        <form>
			  <div class="mb-3">
			    <label for="exampleInputEmail1" class="form-label">Email address</label>
			    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
			    <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
			  </div>
			  <div class="mb-3">
			    <label for="exampleInputPassword1" class="form-label">Password</label>
			    <input type="password" class="form-control" id="exampleInputPassword1">
			  </div>
  		  	  <button type="submit" class="btn btn-primary">Submit</button>
			</form>
	      </div>
	    </div>
	  </div>
	</div>
</section>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
</body>
</html>
