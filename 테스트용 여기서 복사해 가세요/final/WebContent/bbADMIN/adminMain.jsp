<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
	String pageindex = null;
	if(request.getAttribute("pageindex")!= null)
	{
		pageindex = (String) request.getAttribute("pageindex");
	} else pageindex = "memberMenagement.jsp";
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>BB PANK Admin page</title>
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	</head>
	<body>
	<% if(session.getAttribute("AdminAuthority") == null)
		{ %>
			<script type="text/javascript">
				alert("Auth가 없습니다. 메인으로 이동합니다.");
				location.href = "../FrontController?src=main";
			</script>
		<% } %>
		
	<% if(request.getAttribute("success") != null) { 
		String message = (String) request.getAttribute("success"); %>
		<script type="text/javascript">
			alert('<%=message%>');
		</script>
	<% } %>
		<header>
			<nav class = "navbar navbar-default">
				<div class = "container-fluid">
					<div class = "navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#adminNavbar">
					        <span class="icon-bar"></span>
					        <span class="icon-bar"></span>
					        <span class="icon-bar"></span>                  
					     </button>
					</div>
					<div class ="collapse navbar-collapse" id="adminNavbar">
						<ul class="nav navbar-nav">
						  <li class="active"><a href="./AdminFrontController?src=guest">회원관리</li>
						  <li><a href="./AdminFrontController?src=board&contentno=0&pageno=0">게시판 관리</a></li>
						  <li><a href="./AdminFrontController?src=qna&contentno=0&pageno=0">Q&A관리</a></li>
						  <li><a href="./AdminFrontController?src=point&contentno=0&pageno=0">포인트 관리</a></li>
						  <!-- <li><a href="./AdminFrontController?src=style"></a></li>  -->
						  <!-- 페이지 연결 코드입니다. 필요하면 가져다 쓰세요.  -->
						<ul class = "nav navbar-nav navbar-right">
						  <li><a href="./AdminFrontController?src=modify">관리자 설정</a></li>
						  <li><a href="adminLogout.do">Logout</a></li>
						</ul>
				     </div>
				</div>
			</nav>
		</header>		
		<jsp:include page="<%=pageindex %>" />
	</body>
</html>