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
<title>이메일 인증</title>



</head>
<body>
<section>
<form method="post" name="emailCheckSend" action="${pageContext.request.contextPath}/member/emailCertification.do" onsubmit="return emailCerCheck()">
<input type="hidden" name="email" value="${email }">

		<p>인증번호입력<br><br>
		<input type="text"  name="certification" >
		<input type="button" value="인증번호 다시 보내기" onClick="emailCheck(this.form.email.value)"><p>
		<input type="submit" value="인증번호 확인">
</form>
</section>
<script src="script.js"></script>
</body>
</html>
