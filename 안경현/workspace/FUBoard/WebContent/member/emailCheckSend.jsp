<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.lang.String.*" %>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" rel="stylesheet" type="text/css">

<style>

</style>
<title>이메일 인증</title>



</head>
<body>
<section>
<%
String certificationNum = null; 
%>

		<p>인증번호입력<br><br>
		<input type="text"  name="certification" >
		<input type="button" value="인증번호 다시 보내기" onClick=""><p>
		<input type="button" value="인증번호 확인" onClick="">

</section>
<script src="script.js"></script>
</body>
</html>
