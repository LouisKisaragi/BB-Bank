<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "javax.servlet.RequestDispatcher" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="<c:url value="/hairJS/hairjs.js" />"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- header -->
<div id = "header" class = "text-center" style= "background-color:black">
<a href="./FrontController?src=main">
<img src="./hairIMAGE/logo1.png" width="40%" height="20%">
</a>
</div> 


<!-- nav bar 양식  -->
<div style = "background-color : black">
<div class="navbar navbar-inverse" align = "center">
     <div class="container-fluid">
       <ul class="nav navbar-nav">
         <li class="active"><a href="./FrontController?src=main">ホーム</a></li>
          <li><a href="./FrontController?src=introduce">紹介</a></li>
          <li><a href="./FrontController?src=style">スタイル</a></li>
          <li><a href="./FrontController?src=event">イベント</a></li>
           <% 
           if(session.getAttribute("login") != null)
           {%>
          <li><a href="./FrontController?src=booking">予約</a></li>
          <li><a href="./FrontController?src=board">口コミ</a></li>
          <%} %>
       </ul>
       <ul class="nav navbar-nav navbar-right">
       <%
       	if(session.getAttribute("login") == null)
       	{ %>
       		<li><a href="" data-toggle="modal" data-target="#loginmodal"><span class = "glyphicon glyphicon-user">　ログイン</span></a></li>
          <li><a href="./FrontController?src=join"><span class = "glyphicon glyphicon-music">　会員登録</span></a></li>
		 <div class="modal fade" id="loginmodal" role="dialog">
		    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
			<script>
			$(document).ready(function(){	 $("#loginmodal").load(getContextPath()+"/hairJSP/login.jsp");	});
			</script>
		 </div>
		 
       	<%}
       	else {
       %>   
          <li><a href="./FrontController?src=mypage"><span class = "glyphicon glyphicon-user"> マイページ</span></a></li>
          <li><a href="./logout.do"><span class = "glyphicon glyphicon-music"> ログアウト</span></a></li>
       <% } %>
       </ul>
        </div>     
   </div>
   <br>
</div>
</body>
</html>