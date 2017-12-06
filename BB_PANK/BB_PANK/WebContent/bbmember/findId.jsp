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
<title>아이디 찾기 </title>



</head>
<body>
<section>
	<form method="post"  name="findId" action="${pageContext.request.contextPath}/bbmember/findIdSend.do"
	onsubmit="return idFindSave()">
		아이디 찾기<p>
		이름<input type="text" name="name"><br>
		이메일<input type="email" name="email"><br>
		<input type="submit"  value="아이디 찾기" >
		<input type="button"  value="비밀번호 찾기" onClick="document.location.href='${pageContext.request.contextPath}/bbmember/findPass.do'">
		<input type="button" value="이전으로 가기" onClick="javascript:back();">
	</form>
</section>
<script src="script.js"></script>
</body>
</html>
