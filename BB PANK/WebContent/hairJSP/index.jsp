<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String pageindex = null;
    if(request.getAttribute("pageindex") != null)
    {
    	pageindex = (String) request.getAttribute("pageindex");
    } else pageindex = "../hairJSP/main.jsp";
    
    %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/earlyaccess/roundedmplus1c.css" rel="stylesheet" />
<link href="https://fonts.googleapis.com/css?family=Concert+One" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="./hairJS/hairjs.js" ></script>
<title>INDEX</title>
<style>
@import url(http://fonts.googleapis.com/earlyaccess/hanna.css);

body,table,div,p{
font-family: "Hanna", "Concert One","Rounded Mplus 1c";
}
a{
color : white;
}

</style>
</head>
<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="50">
<div class="container-fluid" style = "background-color:black"> <!-- 가장 바깥의 div -->
<jsp:include page = "../hairJSP/header.jsp"></jsp:include>
<jsp:include page = "<%=pageindex %>"></jsp:include>
<jsp:include page = "../hairJSP/footer.jsp"></jsp:include>
</div>
</body>
</html>