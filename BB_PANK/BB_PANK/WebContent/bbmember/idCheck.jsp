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
<script src="script.js"></script>
<style>

</style>
<title>아이디 중복확인 체크</title>



</head>
<body>
<section>
<form name="idCheck" action="${pageContext.request.contextPath}/bbmember/idCheck.do?id=${id }"  onsubmit="return idCheckSave()">
<c:choose>
	<c:when test="${check ==1 }">
		이미 존재하는 ID입니다.
		<input type="text"  name="id"  value="${id }">
		<input type="submit" value="id 중복확인" >
	</c:when>
	<c:when test="${check ==0 }">
		 사용 가능한 아이디입니다.
	
				 <input type="hidden"  name="rid"  value="${id }">
				<p>${id }<p>
	
		<input type="submit" value="이 id 사용하기" >
		<!-- onClick="return idCheckSave();" -->
	</c:when>
</c:choose>
</form>
</section>
<script src="script.js"></script>
</body>
</html>
