<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.lang.String.*" %>
<!DOCTYPE html>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
<link href="${pageContext.request.contextPath}/member/css/style.css" rel="stylesheet" type="text/css">

</head>
<body>
<section>
	<form method="post"  name="login" action="${pageContext.request.contextPath}/member/loginPro.do?"
	onsubmit="return login()">
		<table class="login">
			<tr>
				<th>아이디</th><td><input type="text" name="id"></td>
			</tr>
			<tr>
				<th>비밀번호</th><td><input type="password" name="pass"></td>
			</tr>
			<tr>
				<td><input type="submit" value="로그인" onClick=""></td>
				<td><input type="button" value="아이디/비밀번호찾기"></td>
				<td><input type="button" value="회원가입"></td>
			</tr>	
		</table>
	</form>
</section>
</body>
</html>
