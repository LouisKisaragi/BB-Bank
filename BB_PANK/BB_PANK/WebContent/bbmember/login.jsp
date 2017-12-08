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
<script type="text/javascript">
function back(){
	history.go(-1);
}
</script>
<section>
	<form method="post"  name="logIn" action="${pageContext.request.contextPath}/bbmember/loginPro.do"
	onsubmit="return logInSave()"> 
	<input type="hidden" name="page" value="${page }" >
	<div class="layer1">
		<span class="content">
		<table class="login">
			<tr>
				<th colspan="2">아이디</th><td colspan="2"><input type="text" name="id"></td>
			</tr>
			<tr>
				<th colspan="2">비밀번호</th><td colspan="2"><input type="password" name="pass"></td>
			</tr>
			<tr><td colspan="4">&nbsp;</td></tr>
			<tr>
				<td><input type="submit" value="로그인" ></td>
				<td><input type="button" value="아이디/비밀번호찾기" onClick="document.location.href='${pageContext.request.contextPath }/bbmember/findIdPass.do'"></td>
				<td><input type="button" value="회원가입" onClick="document.location.href='${pageContext.request.contextPath}/bbmember/join.do'"></td>
				<td><input type="button" value="이전으로 돌아가기" onClick="javascript:back();">
			</tr>	
		</table>
		</span>
		<span class="blank"></span>
		</div>
	</form>
</section>
<div id="footer">
<%@ include file="../bbboard/footer.jsp" %>
</div>
<script src="${pageContext.request.contextPath }/bbmember/script.js"></script>
</body>
</html>
