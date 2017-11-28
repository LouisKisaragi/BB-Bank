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

<script type="text/javascript">
function back(){
	history.go(-1);
}
</script>
<style>

</style>
<title>비밀번호 찾기 </title>



</head>
<body>
<section>
	<form method="post"  name="findPass" action="${pageContext.request.contextPath}/member/findPassSend.do"
	onsubmit="return passFindSave()">
	<input type="hidden" name="page" value="${page }">
		비밀번호 찾기<p>
		아이디<input type="text" name="id"><br>
		이메일<input type="email" name="email"><br>
		<input type="button"  value="비밀번호 찾기" onClick="passFind()">
		<input type="button"  value="아이디 찾기" onClick="document.location.href='${pageContext.request.contextPath}/member/findId.do'">
		<input type="button" value="이전으로 가기" onClick="javascript:back();">
	</form>
</section>
<script src="script.js"></script>
</body>
</html>
