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
<script src="${pageContext.request.contextPath}/bbmember/script.js"></script>
<!-- Bootstrap core CSS -->
	<link href="${pageContext.request.contextPath}/Resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom fonts for this template-->
  <link href="${pageContext.request.contextPath}/Resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/Resources/css/modern-business.css" rel="stylesheet">
     <!-- Page level plugin CSS-->
  <link href="${pageContext.request.contextPath}/Resources/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
  <!-- Custom styles for this template-->
  <link href="${pageContext.request.contextPath}/Resources/css/sb-admin.css" rel="stylesheet">
</head>
<body>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
      <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath }/bbadmin/adMain.do">BBBANK adminpage</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
         <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
        <li class="nav-item" >
          <a class="nav-link" href="${pageContext.request.contextPath}/bbadmin/adMainlist.do?pageNum=1&bn=0">
            <span class="nav-link-text">게시판별 공지사항 관리</span>
          </a>
        </li>
        <li class="nav-item">
         <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseComponents" data-parent="#exampleAccordion">
            <span class="nav-link-text">각 게시판별 관리</span>
          </a>
            <ul class="sidenav-second-level collapse" id="collapseComponents">
            <li>
              <a href="${pageContext.request.contextPath}/bbadmin/listM.do?pageNum=1&bn=1">공지사항</a>
            </li>
            <li>
              <a href="${pageContext.request.contextPath}/bbadmin/adlist.do?pageNum=1&bn=4">자유게시판</a>
            </li>
            <li>
              <a href="${pageContext.request.contextPath}/bbadmin/adlist.do?pageNum=1&bn=5">야구게시판</a>
            </li>
             <li>
              <a href="${pageContext.request.contextPath}/bbadmin/adlist.do?pageNum=1&bn=2">Q&A게시판</a>
            </li>
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/bbadmin/admember.do?pageNum=1&bn=2">
            <span class="nav-link-text">회원 관리</span>
          </a>
        </li>
     <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/bbadmin/adlist.do?pageNum=1&bn=3">
            <span class="nav-link-text">사이트 이슈 게시판</span>
          </a>
        </li>
        </ul>
        <ul class="navbar-nav sidenav-toggler">
        <li class="nav-item">
          <a class="nav-link text-center" id="sidenavToggler">
            <i class="fa fa-fw fa-angle-left"></i>
          </a>
        </li>
      </ul>
          <ul class="navbar-nav ml-auto text-right">
        <c:choose>
            	<c:when test="${login eq 1 }">
            <li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath }/bbadmin/adlogout.do">로그아웃</a>
            </li>
            
				</c:when>
            	<c:otherwise>
            <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath }/bbadmin/adlogin.do">관리자로그인</a>
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
     <!-- Core plugin JavaScript-->
    <script src="${pageContext.request.contextPath}/Resources/vendor/jquery-easing/jquery.easing.min.js"></script>
    <!-- Page level plugin JavaScript-->
    <script src="${pageContext.request.contextPath}/Resources/vendor/chart.js/Chart.min.js"></script>
    <script src="${pageContext.request.contextPath}/Resources/vendor/datatables/jquery.dataTables.js"></script>
    <script src="${pageContext.request.contextPath}/Resources/vendor/datatables/dataTables.bootstrap4.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="${pageContext.request.contextPath}/Resources/js/sb-admin.min.js"></script>
    <!-- Custom scripts for this page-->
    <script src="${pageContext.request.contextPath}/Resources/js/sb-admin-datatables.min.js"></script>
    <script src="${pageContext.request.contextPath}/Resources/js/sb-admin-charts.min.js"></script>
</body>
</html>