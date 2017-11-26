<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.lang.String.*" %>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
<link href="${pageContext.request.contextPath}/member/css/style.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/member/css/joinstyle.css" rel="stylesheet" type="text/css"/>
<script src="script.js"></script>
<script src="${pageContext.request.contextPath}/member/script.js"></script>
</head>
<body>
<section>
	<form method="post" name="join" action="${pageContext.request.contextPath}/member/joinPro.do?"
	onsubmit="return joinSave()">
	<b>회원 가입</b>
<!--  	<c:set var="idcheck" value="0"/>-->
	<input type="hidden" name="page" value="${page }">
	<input type="hidden" name="idcheck" value="0" >
	<input type="hidden" name="passcheck" value="0">
<!-- 	<c:set var="passcheck" value="0"/>-->
	<table class="jointable">
		<tr>
			<th>아이디</th><td><input type="text" name="id" required></td>
			<td><input type="button" value="아이디 중복확인" onClick="idCheck(this.form.id.value)">	</td>
		</tr>
		<tr>
			<th>비밀번호</th><td><input type="password" name="pass" required></td>
		</tr>
		<tr>
			<th>비밀번호재확인</th><td><input type="password" name="repass" required></td><td><input type="button" value="비밀번호재확인" onClick ="passCheck(this.form.pass.value,this.form.repass.value)"></td>
		</tr>
		<tr>
			<th>이름</th><td><input type="text" name="name" required></td>
		</tr>
		<tr>
			<th>이메일</th><td><input type="email" name="email" required></td>
		</tr>
	</table>
	<table class="joinbutton">
	<tr><td><input type="submit" value="회원가입"></td></tr>
		<!--<c:choose>
			<c:when test="${idcheck==0 }">
				<tr><td>아이디 중복체크를 해주세요!<input type="button" value="회원가입" onClick="return joinSave();"></td></tr>
			</c:when>
			<c:when test="${passcheck==0 }">
				<tr><td>비밀번호 재확인을 해주세요!</td></tr>
			</c:when>
			<c:otherwise>-->
				
	<!-- 		</c:otherwise>
		</c:choose>-->
	</table>
	</form>
</section>

</body>
</html>