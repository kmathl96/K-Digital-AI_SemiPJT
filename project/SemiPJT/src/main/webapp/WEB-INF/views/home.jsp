<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>K-Digital Community</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.2/css/all.css" integrity="sha384-vSIIfh2YWi9wW0r9iZe7RJPrKwp6bG+s9QZMoITbCckVJqGCCRhc+ccxNcdpHuYu" crossorigin="anonymous">
	<style>
		body {
			background-color: Cornsilk;
		}
		section {
			margin: 50px 100px;
		}
		article {
			border-radius: 50px;
		}
		#mypage_profileImg {
			width: 150px;
			height: 150px;
		}
		.comment_profileImg {
			width: 30px;
			height: 30px;
		}
	</style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
  <div class="container-fluid">
    <a class="navbar-brand" href="/semipjt/home">K-Digital Community</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link" aria-current="page" href="/semipjt/home">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="" tabindex="-1" aria-disabled="true">Notice</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/semipjt/mypage" tabindex="-1" aria-disabled="true">My Page</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/semipjt/logout">Logout</a>
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
	<jsp:include page="${page}.jsp"/>
</section>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<script type="text/javascript">
$(function(){
    $('ul.nav-tabs a').click(function (e) {
      e.preventDefault()
      $(this).tab('show')
    })
});

// 메뉴가 선택되기 전의 이벤틀르 가져온다.
//$('a[data-toggle="tab"]').on('show.bs.tab', function (e) {
	// 선택되는 요소를 오브젝트화 한다.
//	$this = $(e.target);
	// data-load가 false의 경우는 content에 data-load를 한다.
//	if(!$this.data("load")) {
		// tab-content의 id를 취득한다.
//		var id = $this.attr("href");
		// 페이지 로드를 한다.
//		$(id).load($this.data("url"));
		// data-load를 true로 변환하여 중복 로딩이 없게 한다.
//		$this.data("load", true);
//	}
//});
//$('a[data-toggle="tab"]').on('hide.bs.tab', function (e) {
//});
</script>
</body>
</html>
