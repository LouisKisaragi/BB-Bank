<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String pageindex = null;
	if(request.getAttribute("pageindex")!= null)
	{
		pageindex = (String) request.getAttribute("pageindex");
	} else pageindex = "memberMenagerment.jsp";
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BBC Admin page</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
		
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
						  <li class="active"><a href="./AdminFrontController?src=guest">회원관리</a></li>
						  <li><a href="./AdminFrontController?src=board&contentno=0&pageno=0">게시판 관리</a></li>
						  <li><a href="./AdminFrontController?src=qna&contentno=0&pageno=0">Q&A</a></li>
						</ul>
						<ul class = "nav navbar-nav navbar-right">
						  <li><a href="./AdminFrontController?src=modify">관리자 설정</a></li>
						  <li><a href="adminLogout.do">로그 아웃</a></li>
						</ul>
				     </div>
				</div>
			</nav>
		</header>	
</head>
<body>



</body>
</html>