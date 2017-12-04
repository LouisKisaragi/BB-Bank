<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.lang.String.*" %>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/board/css/style.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/board/css/writeFormstyle.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
<style type="text/css">
#footer {

    position:absolute;

    bottom:0;

    width:100%;

    height:70px;   
}

</style>
	
</head>
<body>
<%@ include file="header.jsp" %>
<section>

<%@ include file="mainContent.jsp" %>
</section>


<div id="footer">
<%@ include file="footer.jsp" %>
</div>


    

</body>
</html>