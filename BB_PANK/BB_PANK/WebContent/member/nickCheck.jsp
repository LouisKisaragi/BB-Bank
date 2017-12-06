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
<title>닉네임 중복확인 체크</title>
<script src="script.js"></script>


</head>
<body>
<section>
<form name="nickCheck" action="${pageContext.request.contextPath}/member/nickCheck.do?nick=${nick }"  onsubmit="return nickCheckSave()">
<c:choose>
	<c:when test="${check ==1 }">
		이미 존재하는 닉네임입니다.
		<input type="text"  name="nick"  value="${id }">
		<input type="submit" value="nick 중복확인" >
	</c:when>
	<c:when test="${check ==0 }">
		 사용 가능한 닉네임입니다.
	
				 <input type="hidden"  name="rnick"  value="${nick }">
				<p>${nick }<p>
	
		<input type="button" value="이 닉네임 사용하기" onClick="nickCheckSt()">
	</c:when>
</c:choose>
</form>
</section>
<script src="script.js"></script>
</body>
</html>
