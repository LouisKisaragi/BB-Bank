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
<link href="${pageContext.request.contextPath}/bbmember/css/style.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/bbmember/css/joinstyle.css" rel="stylesheet" type="text/css"/>
<script src="script.js"></script>
<script src="${pageContext.request.contextPath}/bbmember/script.js"></script>
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
<%@ include file="../bbboard/header.jsp" %>
<section>
	<form method="post" name="join" action="${pageContext.request.contextPath}/bbmember/joinPro.do?"
	onsubmit="return joinSave()">
	<b>회원 가입</b>

	<input type="hidden" name="page" value="${page }">
	<input type="hidden" name="idcheck" value="0" >
	<input type="hidden" name="passcheck" value="0">
	<input type="hidden" name="emailcheck" value="0">
	<input type="hidden" name="nickcheck" value="0">
<div class="layer">
		<span class="content">
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
			<th>닉네임</th><td><input type="text" name="nickname" required></td>
			<td><input type="button" value="닉네임 중복확인" onClick="nickCheck(this.form.nickname.value)"></td>
		</tr>
		<tr>
			<th>이메일</th><td><input type="email" name="email" required></td>
			<td><input type="button" value="이메일 인증" onClick="emailCheck(this.form.email.value)"></td>
		</tr>
	</table>
	<table class="joinbutton">
	<tr><td><input type="submit" value="회원가입"></td></tr>
	</table>
	</span>
		<span class="blank"></span>
		</div>
	</form>
</section>
<div id="footer">
<%@ include file="../bbboard/footer.jsp" %>
</div>
</body>
</html>