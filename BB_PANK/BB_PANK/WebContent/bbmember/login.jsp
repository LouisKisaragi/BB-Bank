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
<link href="${pageContext.request.contextPath}/bbmember/css/style.css" rel="stylesheet" type="text/css">

</head>
<body>
<script type="text/javascript">
function back(){
	history.go(-1);
}
</script>
<section>
	<form method="post"  name="logIn" action="${pageContext.request.contextPath}/bbmember/loginPro.do"
	onsubmit="return logInSave()"> 
	<input type="hidden" name="page" value="${page }" >
		<table class="login">
			<tr>
				<th>아이디</th><td><input type="text" name="id"></td>
			</tr>
			<tr>
				<th>비밀번호</th><td><input type="password" name="pass"></td>
			</tr>
			<tr>
				<td><input type="submit" value="로그인" ></td>
				<td><input type="button" value="아이디/비밀번호찾기" onClick="document.location.href='${pageContext.request.contextPath }/bbmember/findIdPass.do'"></td>
				<td><input type="button" value="회원가입" onClick="document.location.href='${pageContext.request.contextPath}/bbmember/join.do'"></td>
				<td><input type="button" value="이전으로 돌아가기" onClick="javascript:back();">
			</tr>	
		</table>
	</form>
</section>
<script src="${pageContext.request.contextPath }/bbmember/script.js"></script>
</body>
</html>
