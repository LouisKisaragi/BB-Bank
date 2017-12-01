<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!-- Bootstrap core CSS -->
	<link href="${pageContext.request.contextPath}/Resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/Resources/css/modern-business.css" rel="stylesheet">
</head>
<body>
<!-- Navigation -->
    <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath }/board/main.do">BBbank</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
        <c:choose>
            	<c:when test="${login eq 1 }">
            <li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath }/member/logout.do">로그아웃</a>
            </li>
            <li class="nav-item">
            	<a class="nav-link" href="#">${logNick}</a>
            </li>
            
				</c:when>
            	<c:otherwise>
            <li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath }/member/join.do">회원가입</a>	
            </li>
            <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath }/member/login.do?pageNum=${pageNum}&bn=${bn}">로그인</a>
            </li>
				</c:otherwise>
          </c:choose>
          
          </ul>
        </div>
      </div>
    </nav>
   
	<!-- Bootstrap core JavaScript -->
    <script src="${pageContext.request.contextPath}/Resources/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/Resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>