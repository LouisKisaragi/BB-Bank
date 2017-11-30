<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String pageindex = null;
    if(request.getAttribute("pageindex") != null)
    {
    	pageindex = (String) request.getAttribute("pageindex");
    } else pageindex = "../hairJSP/main.jsp";
    
    %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>INDEX</title>
</head>
<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="50">
<jsp:include page = "../hairJSP/header.jsp"></jsp:include>
<jsp:include page = "<%=pageindex %>"></jsp:include>
<jsp:include page = "../hairJSP/footer.jsp"></jsp:include>
</body>
</html>