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
<title>아이디 중복확인 체크</title>



</head>
<body>
<section>
<form name="idCheck">
check=${check }<p>
<c:choose>
	<c:when test="${check ==1 }">
		이미 존재하는 ID입니다.
		<input type="text"  name="iid"  value="${id }">
		<input type="button" value="id 중복확인" onClick="idCheck.do?id=${id}&num=${num}"+num+"&pageNum="+pageNum+"&bn="+bn">
	</c:when>
	<c:when test="${check ==0 }">
		 사용 가능한 아이디입니다.
		 <input type="text"  name="iid"  value="${id }">
		<input type="button" value="이 id 사용하기" onClick="return idCheckSave();">
	</c:when>
</c:choose>
</form>
</section>
<script src="script.js"></script>
</body>
</html>
