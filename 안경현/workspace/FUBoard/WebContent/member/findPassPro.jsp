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
<script type="text/javascript">
function back(){
	history.go(-1);
}
</script>
<style>

</style>
<title>비밀번호 확인</title>



</head>
<body>
<section>
<c:choose>
	<c:when test="${check eq 1 }">
		이메일 인증이 완료되었습니다.<p>
		등록된 비밀번호는 ${pass }입니다.
		<input type="button" value="로그인하기" onClick="document.location.href='${pageContext.request.contextPath}/member/login.do?page=${page }'">
		<input type="button" value="확인" onClick="document.location.href='${page}''">
	</c:when>
	<c:when test="${check eq -1 }">
		인증번호가 다릅니다 다시 인증 바랍니다.<p>
		<input type="button" value="종료" onClick="javascript:back();">
	</c:when>
</c:choose>
</section>
<script src="script.js"></script>
</body>
</html>
